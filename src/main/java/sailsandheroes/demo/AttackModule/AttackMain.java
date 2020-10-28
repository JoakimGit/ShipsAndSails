package sailsandheroes.demo.AttackModule;

import sailsandheroes.demo.Enums.GameResult;
import sailsandheroes.demo.Model.Player;
import sailsandheroes.demo.Model.Ship;
import sailsandheroes.demo.Service.ShipService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
// William og Benjamin
public class AttackMain {
    public static List<Boolean> informationToAttack(List<Player> list, Point p1target, Point p2target){
        List<Boolean> shipsHit = new ArrayList<>();
        Ship playerOneShip = list.get(0).getShipList().get(0);
        Ship playerTwoShip = list.get(1).getShipList().get(0);

        //Set power values
        playerOneShip.calculatePowerValue();
        playerTwoShip.calculatePowerValue();

        //Player one shoots
        shipsHit.add(CheckHit.checkHit(playerOneShip, playerTwoShip, p1target));
        //Player two shoots
        shipsHit.add(CheckHit.checkHit(playerTwoShip, playerOneShip, p2target));

        return shipsHit;
    }
}
