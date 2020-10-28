package sailsandheroes.demo.AttackModule;

import sailsandheroes.demo.GameModul.Board;
import sailsandheroes.demo.GameModul.Damage;
import sailsandheroes.demo.Model.Hex;
import sailsandheroes.demo.Model.Ship;

import java.awt.*;
import java.util.Random;

public class CheckHit {

    private static final Board hexboard = new Board();

    static {
        hexboard.fillBoard(12, 6);
    }

    //Benjamin
    public static boolean checkHit(Ship shooter, Ship enemy, Point point){
        Random random = new Random();

        Hex targetHex = findHexFromPoint(point);

        boolean isHit = false;

        //TODO WHAT IF ENEMY DOESN'T MOVE? THE LIST WOULD BE EMPTY NO? if (*statement* || point matches ship current position?)
        //Check center
        if(enemy.getPath().contains(point)){
            double chance = random.nextDouble();
            System.out.println("Chance to hit center: " + chance);
            //40 percent for hit
            if(chance <= 0.4){
                isHit = true;
                //Calculate if crit
                if (chance <= 0.1){
                    //roll if crit hits
                    Damage.criticalShot(shooter, enemy);
                } //Hit, but didn't crit - Calculate normal damage
                else Damage.calculate(shooter, enemy, 0.4);
            }
        }
        //Check north
        if(enemy.getPath().contains(targetHex.getN().getPosition())){
            double chance = random.nextDouble();
            System.out.println("Chance to hit north of center: " + chance);
            //10 percent for hit
            if(chance <= 0.1){
                isHit = true;
                Damage.calculate(shooter, enemy, 0.1);
            }
        }
        //Check north-west
        if(enemy.getPath().contains(targetHex.getnW().getPosition())){
            double chance = random.nextDouble();
            System.out.println("Chance to hit north-west of center: " + chance);
            //10 percent for hit
            if(chance <= 0.1){
                isHit = true;
                Damage.calculate(shooter, enemy, 0.1);
            }
        }
        //Check south-west
        if(enemy.getPath().contains(targetHex.getsW().getPosition())){
            double chance = random.nextDouble();
            System.out.println("Chance to hit south-west of center: " + chance);
            //10 percent for hit
            if(chance <= 0.1){
                isHit = true;
                Damage.calculate(shooter, enemy, 0.1);
            }
        }
        //Check south
        if(enemy.getPath().contains(targetHex.getS().getPosition())){
            double chance = random.nextDouble();
            System.out.println("Chance to hit south of center: " + chance);
            //10 percent for hit
            if(chance <= 0.1){
                isHit = true;
                Damage.calculate(shooter, enemy, 0.1);
            }
        }
        //Check south-east
        if(enemy.getPath().contains(targetHex.getsE().getPosition())){
            double chance = random.nextDouble();
            System.out.println("Chance to hit south-east of center: " + chance);
            //10 percent for hit
            if(chance <= 0.1){
                isHit = true;
                Damage.calculate(shooter, enemy, 0.1);
            }
        }
        //Check north-east
        if(enemy.getPath().contains(targetHex.getnE().getPosition())){
            double chance = random.nextDouble();
            System.out.println("Chance to hit north-east of center: " + chance);
            //10 percent for hit
            if(chance <= 0.1){
                isHit = true;
                Damage.calculate(shooter, enemy, 0.1);
            }
        }
        return isHit;
    }

    private static Hex findHexFromPoint(Point p) {
        for (Hex hex : hexboard.getHexGrid()) {
            if (hex.getPosition().x == p.x && hex.getPosition().y == p.y) {
                return hex;
            }
        }
        System.out.println("Couldn't find hex with these coords.");
        return new Hex();
    }
}