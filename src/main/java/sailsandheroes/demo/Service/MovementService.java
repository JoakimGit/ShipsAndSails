package sailsandheroes.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sailsandheroes.demo.GameModul.Damage;
import sailsandheroes.demo.Model.Ship;
import sailsandheroes.demo.Movement.Collision;
import sailsandheroes.demo.Movement.Move;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Joakim
@Service
public class MovementService {

    private final Move move;
    private final Collision collision;

    public MovementService() {
        move = new Move();
        collision = new Collision();
    }

    public boolean Move(Ship ship) {
        return move.moveShip(ship);
    }

    public boolean checkCollision(Ship ship1, Ship ship2) {
        List<Point> newShipPositions1 = collision.validateShipsCollisionPath(ship1, ship2);
        List<Point> newShipPositions2 = new ArrayList<>();
        if (newShipPositions1.size() != 2) {
            newShipPositions2 = collision.collisionDestinationHex(ship1, ship2);
        }
        if (newShipPositions1.size() == 2 || newShipPositions2.size() == 2) {
            List<Point> newPositions = newShipPositions1.size() == 2 ? newShipPositions1 : newShipPositions2;
            Damage.collision(ship1, ship2);
            setNewShipDirection(ship1);
            setNewShipDirection(ship2);

            ship1.setPosition(newPositions.get(0));
            ship2.setPosition(newPositions.get(1));
            return true;
        }
        return false;
    }

    public void setNewShipDirection(Ship ship) {
        move.setNewShipDirection(ship);
    }
}
