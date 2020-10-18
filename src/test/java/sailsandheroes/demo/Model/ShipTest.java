package sailsandheroes.demo.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
// William
class ShipTest {
    public final Ship ship = new Ship(1, "William", new Point(1,1), 160.0, 66, 240, 120.0, 4, "front", 33 ,"Cannon Balls", 1);

    @Test
    void calculatePowerValue() {
        ship.calculatePowerValue();
        assertEquals(33,ship.getPowerValue());
    }

    @Test
    void shipIdTest() {
        assertEquals(1,ship.getShip_id());
        ship.setShip_id(5);
        assertEquals(5,ship.getShip_id());
    }

    @Test
    void shipNameTest() {
        assertEquals("William",ship.getName());
        ship.setName("Johan");
        ship.setName("Benjamin");
        assertEquals("Benjamin",ship.getName());
    }

    @Test
    void shipPositionTest() {
        Point point = new Point(1,1);
        assertEquals(point,ship.getPosition());
        point = new Point(1,2);
        ship.setPosition(point);
        assertEquals(point,ship.getPosition());
    }

    @Test
    void getHullQuality() {

    }

    @Test
    void setHullQuality() {
    }

    @Test
    void getAmountOfGuns() {
    }

    @Test
    void setAmountOfGuns() {
    }

    @Test
    void getAmountOfSailors() {
    }

    @Test
    void setAmountOfSailors() {
    }

    @Test
    void getSailQuality() {
    }

    @Test
    void setSailQuality() {
    }

    @Test
    void getSpeed() {
    }

    @Test
    void setSpeed() {
    }

    @Test
    void getDirection() {
    }

    @Test
    void setDirection() {
    }

    @Test
    void getNationality() {
    }

    @Test
    void setNationality() {
    }

    @Test
    void getPowerValue() {
    }

    @Test
    void setPowerValue() {
    }

    @Test
    void getAmmunition() {
    }

    @Test
    void setAmmunition() {
    }

    @Test
    void getPath() {
    }

    @Test
    void setPath() {
    }
}