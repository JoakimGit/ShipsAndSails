package sailsandheroes.demo.Movement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sailsandheroes.demo.Model.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {

    Ship ship1 = new Ship();
    Ship ship2 = new Ship();

    List<Point> ship1Path = new ArrayList<>();
    List<Point> ship2Path = new ArrayList<>();

    @BeforeEach
    public void init() {
        ship1.setShip_id(1);
        ship1Path.add(new Point(1, 1));
        ship1Path.add(new Point(2, 2));
        ship1Path.add(new Point(3, 3));
        ship1Path.add(new Point(4, 4));

        ship2.setShip_id(2);
        ship2Path.add(new Point(1,1));
        ship2Path.add(new Point(2,2));
        ship2Path.add(new Point(3,3));

    }

    @Test
    void validateShipId() {
       assertEquals(1, ship1.getShip_id());
       assertEquals(2, ship2.getShip_id());
    }

    @Test
    void equalsTruevalidateShipsCollisionPath() {

    }

    @Test
    void equalsFalsevalidateShipsCollisionPath() {

    }

    @Test
    void validateShipsCollisionLastPosition() {
        assertEquals(new Point(4,4),ship1Path.get(3));
    }
}