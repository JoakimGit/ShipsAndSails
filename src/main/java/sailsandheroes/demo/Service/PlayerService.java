package sailsandheroes.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sailsandheroes.demo.Model.Player;
import sailsandheroes.demo.Model.Ship;
import sailsandheroes.demo.Repository.PlayerRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ShipService shipService;

    public Player fetchPlayerById(int id) {
        Player player = playerRepository.fetchPlayerById(id);
        player.setShip(playerRepository.fetchShipsToPlayer(player));
        return player;
    }

    public List<Player> fetchAllPlayers() {
        return playerRepository.fetchAllPlayers();
    }

    public int createPlayer(Player p, String shipname, String direction, Point start) {
        int playerID = playerRepository.createPlayer(p);
        p.setPlayer_id(playerID);

        Ship newShip = shipService.fetchShipByName(shipname);
        newShip.setDirection(direction);
        newShip.setPosition(start);
        shipService.createShip(newShip);
        int shipID = shipService.findNewestReservationId();
        newShip.setShip_id(shipID);

        List<Ship> playerships = new ArrayList<>();
        playerships.add(newShip);
        p.setShip(playerships);

        for (Ship ship : p.getShipList()) {
            playerRepository.createPlayerShip(p, ship);
        }
        return playerID;
    }

    /*public void createPlayer(Player p) {
        playerRepository.createPlayer(p);
        int playerID = findNewestPlayerId();
        p.setPlayer_id(playerID);
        for (Ship ship : p.getShipList()) {
            playerRepository.createPlayerShip(p, ship);
        }
    }*/

    public void updatePlayer(Player p) {
        playerRepository.updatePlayer(p);
    }

    public void deletePlayer(int id) {
        playerRepository.deletePlayer(id);
    }

    public int fetchPlayerIDByShipID(int id) {
        return playerRepository.fetchPlayerIDByShipID(id);
    }

    public int findNewestPlayerId() {
        return playerRepository.findNewestPlayerId();
    }

}
