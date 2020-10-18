package sailsandheroes.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private List<Player> playerList = new ArrayList<>();
    private boolean player1TurnSuccess;

    @Autowired
    MovementService movementService;

    @Autowired
    ShipService shipService;

    // tag imod playerOrder objekt. Instantier enums og switch på PlayerNumber og Action enums.
    // Kald metoder fra Movement og Attack "lag".
    // Modtag GameResult enum fra Attack
    // Kommuniker op igen til Game eller CommmunicationController

    public PlayerOrderResult recievePlayer(Player player, boolean isAttack){
        if (!isAttack) {
            action = Action.MOVE;
        } else action = Action.ATTACK;
        this.player = player;
        playerNumber = designatePlayerNumber();

        PlayerOrderResult playerOrderResult;
        return playerOrderResult = routeAndAskForGameResult();
    }

    public PlayerOrderResult routeAndAskForGameResult(){
        PlayerOrderResult playerOrderResult = new PlayerOrderResult();
        switch (playerNumber) {
            case PLAYER1:
                switch (action){
                    case MOVE:
                        System.out.println("GameController: player 1 move");
                        Ship player1Ship = player.getShipList().get(0);

                        if(!movementService.Move(player1Ship)){
                            playerOrderResult.setTurnResult(TurnResult.FAILED);
                            player1TurnSuccess = false;
                            return playerOrderResult;
                        }

                        player1TurnSuccess = true;

                        return playerOrderResult;

                    case ATTACK:
                        playerList = new ArrayList<>();
                        playerList.add(player);

                        System.out.println("GameController: player 1 til angreb din landkrabbe");

                        return playerOrderResult;
                }
                break;

            case PLAYER2:
                switch (action){
                    case MOVE:
                        System.out.println("GameController: player 2 move");

                        if(!movementService.Move(player.getShipList().get(0))){
                            playerOrderResult.setTurnResult(TurnResult.FAILED);
                            return playerOrderResult;
                        }
                        if (player1TurnSuccess) {
                            Ship player1Ship = playerList.get(0).getShipList().get(0);
                            Ship player2Ship = playerList.get(1).getShipList().get(0);
                            System.out.println(player1Ship);
                            System.out.println(player2Ship);
                            boolean isCollision = movementService.checkCollision(player1Ship, player2Ship);

                            if (!isCollision) {
                                Point ship1dest = player1Ship.getPath().get(player1Ship.getPath().size()-1);
                                Point ship2dest = player2Ship.getPath().get(player2Ship.getPath().size()-1);

                                player1Ship.setPosition(ship1dest);
                                player2Ship.setPosition(ship2dest);
                            }
                            shipService.updateShip(player1Ship);
                            shipService.updateShip(player2Ship);
                            playerList.clear();
                        }
                        return playerOrderResult;

                    case ATTACK:
                        playerList.add(player);
                        playerOrderResult.setGameResult(AttackMain.informationToAttack(playerList));
                        System.out.println("GameController: player 2 til angreb din landkrabbe");

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
}