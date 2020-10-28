package sailsandheroes.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sailsandheroes.demo.AttackModule.WinConditions;
import sailsandheroes.demo.Enums.*;
import sailsandheroes.demo.Enums.PlayerNumber;
import sailsandheroes.demo.AttackModule.AttackMain;
import sailsandheroes.demo.Model.Ship;
import sailsandheroes.demo.Service.MovementService;
import sailsandheroes.demo.Service.ShipService;
import sailsandheroes.demo.Utility.PlayerOrder;
import sailsandheroes.demo.Model.Player;
import sailsandheroes.demo.Movement.*;
import sailsandheroes.demo.Utility.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
// Mads og Michael
@Controller
public class GameController {
    private Action action;
    private PlayerNumber playerNumber;
    private Player player = new Player();
    private final List<Player> playerList = new ArrayList<>();
    private final List<PlayerOrder> playerOrders = new ArrayList<>();
    private Point p1target = new Point();
    private boolean player1MoveSuccess;
    private boolean player2MoveSuccess;


    @Autowired
    MovementService movementService;

    @Autowired
    ShipService shipService;

    // tag imod playerOrder objekt. Instantier enums og switch på PlayerNumber og Action enums.
    // Kald metoder fra Movement og Attack "lag".
    // Modtag GameResult enum fra Attack
    // Kommuniker op igen til Game eller CommmunicationController

    public PlayerOrderResult receivePlayerOrder(PlayerOrder playerOrder){
        action = playerOrder.getAction();
        player = playerOrder.getPlayer();
        playerNumber = designatePlayerNumber();

        if (!playerOrders.contains(playerOrder)) {
            playerOrders.add(playerOrder);
        }

        return routeAndAskForGameResult();
    }

    public PlayerOrderResult routeAndAskForGameResult(){
        PlayerOrderResult playerOrderResult = new PlayerOrderResult();
        if (!playerList.contains(player)) {
            playerList.add(player);
        }
        else if (playerList.contains(player)) {
            for (Player myPlayer : playerList) {
                if (myPlayer.getPlayer_id() == player.getPlayer_id()) {
                    myPlayer.getShipList().get(0).setPath(player.getShipList().get(0).getPath());
                }
            }
        }
        switch (playerNumber) {
            case PLAYER1:
                switch (action){
                    case MOVE:
                        System.out.println("GameController: player 1 move");
                        Ship player1Ship = player.getShipList().get(0);

                        if(!movementService.Move(player1Ship)){
                            playerOrderResult.setTurnResult(TurnResult.FAILED);
                            player1MoveSuccess = false;
                            return playerOrderResult;
                        }

                        player1MoveSuccess = true;

                        return playerOrderResult;

                    case ATTACK:

                        System.out.println("GameController: player 1 til angreb din landkrabbe");

                        for (PlayerOrder po : playerOrders) {
                            if (po.getPlayer().getPlayer_id() % 2 != 0 && po.getAction() == Action.ATTACK) {
                                p1target = po.getTarget();
                            }
                        }

                        return playerOrderResult;
                }
                break;

            case PLAYER2:
                switch (action){
                    case MOVE:
                        System.out.println("GameController: player 2 move");

                        if(!movementService.Move(player.getShipList().get(0))){
                            playerOrderResult.setTurnResult(TurnResult.FAILED);
                            player2MoveSuccess = false;
                            return playerOrderResult;
                        }
                        player2MoveSuccess = true;

                        if (player1MoveSuccess) {
                            Ship player1Ship = playerList.get(0).getShipList().get(0);
                            Ship player2Ship = playerList.get(1).getShipList().get(0);
                            boolean isCollision = movementService.checkCollision(player1Ship, player2Ship);

                            if (!isCollision) {
                                Point ship1dest = player1Ship.getPath().get(player1Ship.getPath().size()-1);
                                Point ship2dest = player2Ship.getPath().get(player2Ship.getPath().size()-1);

                                movementService.setNewShipDirection(player1Ship);
                                movementService.setNewShipDirection(player2Ship);
                                player1Ship.setPosition(ship1dest);
                                player2Ship.setPosition(ship2dest);
                            }
                            shipService.updateShip(player1Ship);
                            shipService.updateShip(player2Ship);

                        }
                        return playerOrderResult;

                    case ATTACK:

                        System.out.println("GameController: player 2 til angreb din landkrabbe");

                        if (player1MoveSuccess && player2MoveSuccess) {
                            Point p2target = new Point();
                            for (PlayerOrder po : playerOrders) {
                                if (po.getPlayer().getPlayer_id() % 2 == 0 && po.getAction() == Action.ATTACK) {
                                    p2target = po.getTarget();
                                }
                            }
                            List<Boolean> shipsHit = AttackMain.informationToAttack(playerList, p1target, p2target);
                            if (shipsHit.get(0)) shipService.updateShip(playerList.get(1).getShipList().get(0));
                            if (shipsHit.get(1)) shipService.updateShip(playerList.get(0).getShipList().get(0));
                            playerOrderResult.setGameResult(WinConditions.checkWinCondition(playerList));
                        }
                        return playerOrderResult;
                }
                break;
        }
        return null;
    }

    public PlayerNumber designatePlayerNumber() {
        if (player.getPlayer_id() % 2 != 0){
            return PlayerNumber.PLAYER1;
        }
        else if (player.getPlayer_id() % 2 == 0){
            return PlayerNumber.PLAYER2;
        }
        else {
            System.out.println("playerID ikke gyldigt");
        }
        return null;
    }

    /*public PlayerOrderResult receivePlayer(Player player, boolean isAttack){
        if (!isAttack) {
            action = Action.MOVE;
        } else action = Action.ATTACK;
        this.player = player;
        playerNumber = designatePlayerNumber();

        PlayerOrderResult playerOrderResult;
        return playerOrderResult = routeAndAskForGameResult();
    }*/
}