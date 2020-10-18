package sailsandheroes.demo.AttackModule;

import org.junit.jupiter.api.Test;
import sailsandheroes.demo.Enums.GameResult;
import sailsandheroes.demo.Model.Player;
import sailsandheroes.demo.Model.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
// William
class WinConditionsTest {

    @Test
    void checkWinCondition() {
        // Creates ships
        Ship ship1 = new Ship(1, "William", new Point(1,1), 160.0, 66, 240, 120.0, 4, "front", 33 ,"Cannon Balls", 1);
        Ship ship2 = new Ship(2, "William", new Point(1,1), 160.0, 66, 240, 120.0, 4, "front", 33 ,"Cannon Balls", 1);
        List<Player> list = new ArrayList<>();

        ArrayList<Ship> shipList = new ArrayList<>();
        shipList.add(ship1);
        ArrayList<Ship> shipList2 = new ArrayList<>();
        shipList2.add(ship2);
        Player pl1 = new Player(shipList,1,"Pl1"); Player pl2 = new Player(shipList2,2,"pl2");
        list.add(pl1); list.add(pl2);
        // Should be none, as there are no ships at death state
        assertEquals(GameResult.NONE,WinConditions.checkWinCondition(list));

        pl1.getShipList().get(0).setHullQuality(0);
        //Should be pl2 that wins, as pl1 has a ship at death state
        assertEquals(GameResult.PLAYER2_WIN,WinConditions.checkWinCondition(list));

        pl2.getShipList().get(0).setHullQuality(0);
        // Should be a draw, as each player has a ship at death state
        assertEquals(GameResult.DRAW,WinConditions.checkWinCondition(list));

        pl1.getShipList().get(0).setHullQuality(1);
        // Should be pl1 tat wins, as he no longer has a ship at death state
        assertEquals(GameResult.PLAYER1_WIN,WinConditions.checkWinCondition(list));

    }
}