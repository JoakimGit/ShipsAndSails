package sailsandheroes.demo.Utility;

import sailsandheroes.demo.Enums.Action;
import sailsandheroes.demo.Model.Player;

import java.awt.*;
import java.util.List;
// Jakob
public class PlayerOrder {

    private Player player;
    private Action action;
    private List<Point> coords;

    public PlayerOrder() {
    }

    public PlayerOrder(Player player, Action action, List<Point> coords) {
        this.player = player;
        this.action = action;
        this.coords = coords;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Point> getCoords() {
        return coords;
    }

    public void setCoords(List<Point> coords) {
        this.coords = coords;
    }

    @Override
    public String toString() {
        return "playerID = " +  player.getPlayer_id() + " action = " + action + " Coord list =  " + coords;
    }
}
