package sailsandheroes.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sailsandheroes.demo.Enums.TurnResult;
import sailsandheroes.demo.GameModul.Game;
import sailsandheroes.demo.Model.Player;
import sailsandheroes.demo.Service.GameService;
import sailsandheroes.demo.Service.MovementService;
import sailsandheroes.demo.Service.PlayerService;
import sailsandheroes.demo.Service.ShipService;
import sailsandheroes.demo.Utility.PlayerOrder;
import sailsandheroes.demo.Utility.PlayerOrderResult;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Joakim
@Controller
public class CommunicationController {

    @Autowired
    ShipService shipService;

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @Autowired
    GameController gameController;

    @GetMapping("/pregame")
    public String preGame() {
        return "/pregame";
    }

    @PostMapping("/pregame")
    public String preGameComplete(WebRequest param, RedirectAttributes redirect) {

        int player1ID = playerService.createPlayer(new Player(param.getParameter("player1name")), param.getParameter("ship1"),
                param.getParameter("direction1"), new Point(1, 1));

        int player2ID = playerService.createPlayer(new Player(param.getParameter("player2name")), param.getParameter("ship2"),
                param.getParameter("direction2"), new Point(9, 3));

        Player player1 = playerService.fetchPlayerById(player1ID);
        Player player2 = playerService.fetchPlayerById(player2ID);

        Game game = new Game(12, 6, player1, player2);
        int gameID = gameService.createGame(game);
        game.setGame_id(gameID);

        redirect.addAttribute("gameID", gameID);
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String board(Model model, @RequestParam("gameID") int gameID){
        Game game = gameService.fetchGameById(gameID);
        game.setPlayer1(playerService.fetchPlayerById(game.getPlayer1_id()));
        game.setPlayer2(playerService.fetchPlayerById(game.getPlayer2_id()));

        model.addAttribute("ship1", game.getPlayer1().getShipList().get(0));
        model.addAttribute("ship2", game.getPlayer2().getShipList().get(0));
        model.addAttribute("list", game.getBoard().getHexGrid());
        model.addAttribute("gameid", game.getGame_id());

        return "/game";
    }

    @PostMapping("/movement")
    public String movement(@RequestBody String data, RedirectAttributes redirect) {
        String[] moves = data.split(",");
        int shipID = Integer.parseInt(moves[0]);
        int gameID = Integer.parseInt(moves[moves.length-1]);
        int playerID = playerService.fetchPlayerIDByShipID(shipID);

        List<Point> shipPath = findPath(moves);
        List<String> targetList = findTarget(moves);

        Player player = playerService.fetchPlayerById(playerID);
        player.getShipList().get(0).setPath(shipPath);
        PlayerOrder playerOrderMove = makePlayerOrderMove(player);
        PlayerOrder playerOrderAttack = makePlayerOrderAttack(player, targetList);

        System.out.println(player);
        System.out.println(shipPath);
        PlayerOrderResult turnResult = gameController.receivePlayerOrder(playerOrderMove);
        if (turnResult.getTurnResult() == TurnResult.SUCCESSFUL && playerOrderAttack != null) {
            gameController.receivePlayerOrder(playerOrderAttack);
        }

        redirect.addAttribute("gameID", gameID);
        return "redirect:/board";
    }

    private List<Point> findPath(String[] testArray) {
        List<Point> myPoints = new ArrayList<>();
        for (int i = 1, j = 2; i < testArray.length; i+=2) {
            if (testArray[i].length() > 3) {
                break;
            }
            int x = Integer.parseInt(testArray[i]);
            int y = Integer.parseInt(testArray[j]);
            myPoints.add(new Point(x, y));
            j += 2;
        }
        return myPoints;
    }

    private List<String> findTarget(String[] array) {
        List<String> targetList = new ArrayList<>();
        int countBools = 0;
        for (String s : array) {
            if (s.length() > 3 && countBools < 1) {
                targetList.add(s);
                countBools++;
                continue;
            }
            if (s.length() < 3 && countBools > 0 ) {
                targetList.add(s);
            }
        }
        return targetList;
    }

    private PlayerOrder makePlayerOrderAttack(Player player, List<String> attackList) {
        boolean isAttack;
        Point target = null;
        PlayerOrder playerOrderAttack = null;

        if (attackList.size() > 3) {
            isAttack = Boolean.parseBoolean(attackList.get(0));
            target = new Point(Integer.parseInt(attackList.get(1)), Integer.parseInt(attackList.get(2)));
            playerOrderAttack = new PlayerOrder(player, isAttack, target);
        }

        return playerOrderAttack;
    }

    private PlayerOrder makePlayerOrderMove(Player player) {
        boolean isMove = false;
        Point target = null;

        return new PlayerOrder(player, isMove, target);
    }
}
