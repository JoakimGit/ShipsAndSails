package sailsandheroes.demo.Utility;

import sailsandheroes.demo.Enums.Action;
import sailsandheroes.demo.Model.Player;

import java.awt.*;
import java.util.List;
// Jakob
public class PlayerOrderMaker {

    public static PlayerOrder createPlayerOrder(boolean isAction, Point target, String shipID, List<Player> players) throws NullPointerException{
        Action action = Action.MOVE;
        if(isAction){
            action = Action.ATTACK;
        }
        for (Player player : players) {
            if (Integer.parseInt(shipID) == player.getShipList().get(0).getShip_id()) {
                return new PlayerOrder(player, isAction, target);
            }
        }
        return null;
    }
}
