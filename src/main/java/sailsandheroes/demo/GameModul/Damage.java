package sailsandheroes.demo.GameModul;

import sailsandheroes.demo.Model.Ship;

import java.util.Random;

public class Damage {
    // Benjamin
    public static int calculate(Ship shooter, Ship enemy, double hitChance){
        //damage formular (p. 16)
        // hitChance * Firing Power
        double damage = hitChance * shooter.getPowerValue();

        switch(shooter.getAmmunition()){
            case "Cannon Ball":
                enemy.setHullQuality(enemy.getHullQuality() - damage);
                break;
            case "Chain Shot":
                enemy.setSailQuality(enemy.getSailQuality() - damage);
                break;
            case "Grape Shot":
                enemy.setAmountOfSailors((int)(enemy.getAmountOfSailors() - damage));
                break;
        }
        return -1;
    }
    // William
    public static void criticalShot(Ship shooter, Ship enemy, Random random){
        int crit = random.nextInt(20)+1;
            switch(shooter.getAmmunition()){
                // Ship explodes and hull health is set to 0
                case "Cannon Ball":
                    if(crit == 1) {
                        enemy.setHullQuality(0);
                        //TODO kill ship
                    }else if(crit == 2){
                        // TODO no more turning
                    }else
                        break;
                // x amount of sails is destroyed and set sail health to 0
                case "Chain Shot":
                    if(crit == 1) {
                        enemy.setSailQuality(0);
                    }else if(crit == 2){
                        enemy.setSailQuality(enemy.getSailQuality()- enemy.getSailQuality()/3);
                    }else
                        break;
                // Kill % of the sailors on a ship
                case "Grape Shot":
                    if(crit == 1){
                        enemy.setAmountOfSailors(enemy.getAmountOfSailors()/2);
                    }else if(crit == 2){
                        enemy.setAmountOfSailors(enemy.getAmountOfSailors()-enemy.getAmountOfSailors()/4);
                    }else
                        break;
            }
        }
    // MOVEMENT GUYS
    public static void collision(Ship ship1, Ship ship2) {
        double ship1Hull = ship1.getHullQuality();
        double ship2Hull = ship2.getHullQuality();

        double damageDealtByShip1 = ship1Hull/3;
        double damageDealtByShip2 = ship2Hull/3;

        double newShip1Hull = ship1Hull - damageDealtByShip2;
        double newShip2Hull = ship2Hull - damageDealtByShip1;

        ship1.setHullQuality(newShip1Hull);
        ship2.setHullQuality(newShip2Hull);
    }
}
