package sailsandheroes.demo.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sailsandheroes.demo.Enums.Action;
import sailsandheroes.demo.Utility.PlayerOrder;
import sailsandheroes.demo.Utility.PlayerOrderMaker;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerOrderMakerTest {
    boolean isAction;
    Point target;
    String shipID;
    List<Player> players;

    @BeforeEach
    public void setupForTests(){
        isAction = true;
        target = new Point(0,5);
        players = new ArrayList<>();
        for(int j = 1; j <= 2; j++){
            Ship ship = new Ship();
            ship.setShip_id(j);
            Player player = new Player();
            player.setPlayer_id(j);
            //player.setShip(ship); //TODO, det skal ændres så det passer med en list
            players.add(player);
        }
        shipID = "1";
    }

    @Test
    public void createPOtest1() throws NullPointerException{
        PlayerOrder playerOrder = PlayerOrderMaker.createPlayerOrder(isAction,target,shipID,players);
        assertEquals(Action.MOVE,playerOrder.getAction());
    }

    @Test
    public void createPOtest2() throws NullPointerException{
        PlayerOrder playerOrder = PlayerOrderMaker.createPlayerOrder(isAction,target,shipID,players);
        assertEquals(Action.ATTACK,playerOrder.getAction());
    }

}