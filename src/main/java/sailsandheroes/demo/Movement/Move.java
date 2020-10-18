package sailsandheroes.demo.Movement;

import sailsandheroes.demo.GameModul.Board;
import sailsandheroes.demo.Model.Hex;
import sailsandheroes.demo.Model.Ship;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
// Joakim
public class Move {

    private final Board hexboard = new Board();
    private Hex startHex;
    private final Map<String, Integer> directions = new HashMap<>();
    {
        hexboard.fillBoard(12, 6);
        directions.put("North", 1);
        directions.put("Northeast", 2);
        directions.put("Southeast", 3);
        directions.put("South", 4);
        directions.put("Southwest", 5);
        directions.put("Northwest", 6);
    }

    public Move() {
    }

    private String validMove(Ship myShip, Point p) {
        String direction = myShip.getDirection();
        System.out.println("Current direction: " + direction);

        if (!startHex.isEdge()) {
            if (startHex.getNeighbor(direction).getPosition().equals(p.getLocation())) {
                return direction;
            }
        }

        int dirval = directions.get(direction);
        try {
            int turnLeftVal = dirval - 1;
            if (turnLeftVal == 0) {
                turnLeftVal = 6;
            }
            String turnleft = getKeyByValue(turnLeftVal);
            if (startHex.getNeighbor(turnleft).getPosition().equals(p.getLocation())) {
                direction = turnleft;
                return direction;
            }
        }
        catch (NullPointerException e) {
            System.out.println("Checking left failed, lets try the other direction");
        }
        try {
            int turnRightVal = dirval + 1;
            if (turnRightVal == 7) {
                turnRightVal = 1;
            }
            String turnright = getKeyByValue(turnRightVal);
            if (startHex.getNeighbor(turnright).getPosition().equals(p.getLocation())) {
                direction = turnright;
                return direction;
            }
        }
        catch (NullPointerException e) {
            System.out.println("Checking right failed, lets try the other direction");
        }

        System.out.println("Ship " + myShip.getShip_id() + " cannot go to field " + p.getLocation() + " from position " + startHex.getPosition());

        return "false";
    }

    public boolean moveShip(Ship myShip) {

        findStartingHex(myShip);

        for (Point p : myShip.getPath()) {
            String direction = validMove(myShip, p);
            if (!(direction.equals("false"))) {
                myShip.setDirection(direction);
            }
            if (direction.equals("false")) {
                return false;
            }

            switch (direction) {
                case "North":
                    if (startHex.getN().getPosition().equals(p)) {
                        startHex = findHexFromPoint(p);
                        System.out.println("You've successfully moved north");
                    }
                    else System.out.println("You cannot sail north");
                    break;
                case "South":
                    if (startHex.getS().getPosition().equals(p)) {
                        startHex = findHexFromPoint(p);
                        System.out.println("You've successfully moved south");
                    }
                    else System.out.println("You cannot sail south");
                    break;
                case "Northwest":
                    if (startHex.getnW().getPosition().equals(p)) {
                        startHex = findHexFromPoint(p);
                        System.out.println("You've successfully moved northwest");
                    }
                    else System.out.println("You cannot sail northwest");
                    break;
                case "Northeast":
                    if (startHex.getnE().getPosition().equals(p)) {
                        startHex = findHexFromPoint(p);
                        System.out.println("You've successfully moved northeast");
                    }
                    else System.out.println("You cannot sail northeast");
                    break;
                case "Southwest":
                    if (startHex.getsW().getPosition().equals(p)) {
                        startHex = findHexFromPoint(p);
                        System.out.println("You've successfully moved southwest");
                    }
                    else System.out.println("You cannot sail southwest");
                    break;
                case "Southeast":
                    if (startHex.getsE().getPosition().equals(p)) {
                        System.out.println("You've successfully moved southeast");
                        startHex = findHexFromPoint(p);
                    }
                    else {
                        System.out.println("You cannot sail southeast");
                    }
                    break;
            }
        }
        //myShip.setPosition(startHex.getPosition());
        return true;
    }

    private void findStartingHex(Ship myShip) {
        for (Hex hex : hexboard.getHexGrid()) {
            if (hex.getPosition().x == myShip.getPosition().x && hex.getPosition().y == myShip.getPosition().y) {
                startHex = hex;
                System.out.println("Found starting hex: " + startHex);
            }
        }
    }

    private Hex findHexFromPoint(Point p) {
        for (Hex hex : hexboard.getHexGrid()) {
            if (hex.getPosition().x == p.x && hex.getPosition().y == p.y) {
                return hex;
            }
        }
        System.out.println("Couldn't find hex with these coords.");
        return new Hex();
    }

    private String getKeyByValue(int value) {
        for (Map.Entry<String, Integer> entry : directions.entrySet()) {
            if (value == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }
}
