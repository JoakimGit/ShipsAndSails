package sailsandheroes.demo.Utility;

import sailsandheroes.demo.Enums.Action;
import sailsandheroes.demo.Model.Player;

import java.awt.*;
import java.util.List;
// Jakob
public class PlayerOrder {

    private Player player;
    private Action action;
    private Point target;

    public PlayerOrder() {
    }

    public PlayerOrder(Player player, boolean action) {
        this.player = player;
        this.action = action ? Action.ATTACK : Action.MOVE;
    }

    public PlayerOrder(Player player, boolean action, Point target) {
        this.player = player;
        this.action = action ? Action.ATTACK : Action.MOVE;
        this.target = target;
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

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "playerID = " +  player.getPlayer_id() + " action = " + action + " Attack target =  " + target;
    }
}
