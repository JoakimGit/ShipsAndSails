package sailsandheroes.demo.Movement;

import sailsandheroes.demo.GameModul.Board;
import sailsandheroes.demo.Model.Hex;
import sailsandheroes.demo.Model.Ship;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Joakim
public class Move {

    private final Board hexboard = new Board();
    private Hex startHex;
    private final Map<String, Integer> directions = new HashMap<>();
    private String direction;
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

    public boolean moveShip(Ship myShip) {
        findStartingHex(myShip);
        direction = myShip.getDirection();

        for (Point p : myShip.getPath()) {
            boolean isValidMove = validMove(p);
            if (!isValidMove) {
                return false;
            }
        }
        return true;
    }

    private boolean validMove(Point p) {
        System.out.println("Current direction: " + direction);

        if (travel(p, direction)) return true;

        String turnLeftDirection = findLeftDirection();
        if (travel(p, turnLeftDirection)) return true;

        String turnRightDirection = findRightDirection();
        if (travel(p, turnRightDirection)) return true;

        System.out.println("Ship cannot go to field " + p.getLocation() + " from position " + startHex.getPosition());

        return false;
    }

    private boolean travel(Point p, String direction) {
        try {
            if (startHex.getNeighbor(direction).getPosition().equals(p.getLocation())) {
                startHex = startHex.getNeighbor(direction);
                this.direction = direction;
                System.out.println("You've succesfully moved " + direction);
                return true;
            }
        }
        catch (NullPointerException e) {
            System.out.println("Cannot move " + direction + " from here");
        }
        return false;
    }

    private String findLeftDirection() {
        int turnLeftVal = directions.get(direction) - 1;
        if (turnLeftVal == 0) {
            turnLeftVal = 6;
        }
        return getKeyByValue(turnLeftVal);
    }

    private String findRightDirection() {
        int turnRightVal = directions.get(direction) + 1;
        if (turnRightVal == 7) {
            turnRightVal = 1;
        }
        return getKeyByValue(turnRightVal);
    }

    private void findStartingHex(Ship myShip) {
        for (Hex hex : hexboard.getHexGrid()) {
            if (hex.getPosition().x == myShip.getPosition().x && hex.getPosition().y == myShip.getPosition().y) {
                startHex = hex;
                System.out.println("Found starting hex: " + startHex);
            }
        }
    }

    private String getKeyByValue(int value) {
        for (Map.Entry<String, Integer> entry : directions.entrySet()) {
            if (value == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void setNewShipDirection(Ship ship) {
        findStartingHex(ship);
        direction = ship.getDirection();

        for (Point p : ship.getPath()) {
            if (travel(p, direction)) continue;

            String turnLeftDirection = findLeftDirection();
            if (travel(p, turnLeftDirection)) continue;

            String turnRightDirection = findRightDirection();
            travel(p, turnRightDirection);
        }
        ship.setDirection(direction);
    }
}
