package sailsandheroes.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sailsandheroes.demo.Enums.TurnResult;
import sailsandheroes.demo.GameModul.Damage;
import sailsandheroes.demo.Model.Ship;
import sailsandheroes.demo.Movement.Collision;
import sailsandheroes.demo.Movement.Move;
// Joakim
@Service
public class MovementService {

    private final Move move;
    private final Collision collision;

    @Autowired
    ShipService shipService;

    public MovementService() {
        move = new Move();
        collision = new Collision();
    }

    public boolean Move(Ship ship) {
        /*boolean succesfulMove = move.moveShip(ship);
        if (succesfulMove) {
            shipService.updateShip(ship);
        }
        return succesfulMove;*/
        return move.moveShip(ship);
    }

    public boolean checkCollision(Ship ship1, Ship ship2) {
        boolean isCollision = collision.validateShipsCollisionPath(ship1, ship2);
        if (isCollision) {
            Damage.collision(ship1, ship2);
        }
        return isCollision;

    }




}
