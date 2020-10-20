package sailsandheroes.demo.Movement;

import sailsandheroes.demo.Model.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//made by Frederik N
public class Collision {

    // Check to see if they collided at some point during their path
    public List<Point> validateShipsCollisionPath(Ship ship1, Ship ship2) {
        List<Point> ship1_path = ship1.getPath();
        List<Point> ship2_path = ship2.getPath();
        List<Point> finalShipPositions = new ArrayList<>();

        int shorterPath = Math.min(ship1_path.size(), ship2_path.size());
        for (int i = 0; i < shorterPath; i++) {
            if(ship1_path.get(i).equals(ship2_path.get(i))) {
                System.out.println("Collision at: " + ship1_path.get(i));
                if (i > 0) {
                    ship1_path.subList(i, ship1_path.size()).clear();
                    ship2_path.subList(i, ship2_path.size()).clear();
                    finalShipPositions.add(ship1_path.get(i-1));
                    finalShipPositions.add(ship2_path.get(i-1));
                }
                return finalShipPositions;
            }
        }
        return finalShipPositions;
    }

    // Check to see if they collided by ending up at the same hex
    public List<Point> collisionDestinationHex(Ship ship1, Ship ship2) {
        List<Point> ship1_path = ship1.getPath();
        List<Point> ship2_path = ship2.getPath();
        List<Point> finalShipPositions = new ArrayList<>();

        if(ship1_path.get(ship1_path.size()-1).equals(ship2_path.get(ship2_path.size()-1))) {
            System.out.println("Collision at: " + ship1_path.get(ship1_path.size()-1));

            int count = 2;
            Point ship1LastHex = ship1_path.get(ship1_path.size()-count);
            Point ship2LastHex = ship2_path.get(ship2_path.size()-count);

            while (ship1LastHex.equals(ship2LastHex)) {
                count++;
                ship1LastHex = ship1_path.get(ship1_path.size()-count);
                ship2LastHex = ship2_path.get(ship2_path.size()-count);
            }
            int ship1_index = ship1_path.indexOf(ship1LastHex);
            int ship2_index = ship2_path.indexOf(ship2LastHex);

            ship1_path.subList(ship1_index+1, ship1_path.size()).clear();
            ship2_path.subList(ship2_index+1, ship2_path.size()).clear();

            finalShipPositions.add(ship1LastHex);
            finalShipPositions.add(ship2LastHex);
        }
        return finalShipPositions;
    }
}
