package sailsandheroes.demo.Movement;

import sailsandheroes.demo.Model.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//made by Frederik N
public class Collision {

    public boolean validateShipsCollisionPath(Ship ship1, Ship ship2) {
        List<Point> ship1_path = ship1.getPath();
        List<Point> ship2_path = ship2.getPath();

        int shorterPath = Math.min(ship1_path.size(), ship2_path.size());
        // Check to see if they collided at some point during their path
        for (int i = 0; i < shorterPath; i++) {
            if(ship1_path.get(i).equals(ship2_path.get(i))) {
                System.out.println("Collision at: " + ship1_path.get(i));
                if (i > 0) {
                    ship1.setPosition(ship1_path.get(i-1));
                    ship2.setPosition(ship2_path.get(i-1));
                }
                return true;
            }
        }
        // Check to see if they collided by ending up at the same hex
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
            ship1.setPosition(ship1LastHex);
            ship2.setPosition(ship2LastHex);
            return true;
        }
        return false;
    }
}
