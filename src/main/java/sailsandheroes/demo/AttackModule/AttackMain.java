package sailsandheroes.demo.AttackModule;

import sailsandheroes.demo.Enums.GameResult;
import sailsandheroes.demo.Model.Player;
import sailsandheroes.demo.Model.Ship;

import java.awt.*;
import java.util.List;
// William og Benjamin
public class AttackMain {
    public static GameResult informationToAttack(List<Player> list, Point p1target, Point p2target){
        Ship playerOneShip = list.get(0).getShipList().get(0);
        Ship playerTwoShip = list.get(1).getShipList().get(0);

        //Set power values
        playerOneShip.calculatePowerValue();
        playerTwoShip.calculatePowerValue();

        //Player one shoots
        CheckHit.checkHit(playerOneShip, playerTwoShip, p1target);
        //Player two shoots
        CheckHit.checkHit(playerTwoShip, playerOneShip, p2target);

        //none - no result
        return WinConditions.checkWinCondition(list);
    }
}
