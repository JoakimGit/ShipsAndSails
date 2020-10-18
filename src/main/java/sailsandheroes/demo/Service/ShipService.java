package sailsandheroes.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sailsandheroes.demo.Model.Ship;
import sailsandheroes.demo.Repository.ShipRepository;

import java.awt.*;

// Joakim
@Service
public class ShipService {

    @Autowired
    ShipRepository shipRepository;

    public Ship fetchShipById(int id) {
        return shipRepository.fetchShipById(id);
    }

    public void createShip(Ship s) {
        shipRepository.createShip(s);
    }

    public Ship fetchShipByName(String name) {
        Ship shipToReturn;
        switch (name) {
            case "Brig":
                shipToReturn = new Ship(null, "Brig", null, 25, 8, 60, 4, 2, null, 4, null, 1);
                break;
            case "Ship of the Line":
                shipToReturn = new Ship(null, "Ship of the Line", null, 60, 32, 160, 10, 5, null, 16, null, 2);
                break;
            case "Man at War":
                shipToReturn = new Ship(null, "Man at War", null, 140, 84, 340, 24, 4, null, 42, null, 1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
        return shipToReturn;
    }

    public void updateShip(Ship ship) {
        shipRepository.updateShip(ship);
    }

    public int findNewestReservationId() {
        return shipRepository.findNewestShipId();
    }


}
