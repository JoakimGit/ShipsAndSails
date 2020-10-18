package sailsandheroes.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sailsandheroes.demo.Model.Ship;
import sailsandheroes.demo.Utility.ShipRowMapper;

import java.util.ArrayList;
import java.util.List;
// Joakim
@Repository
public class ShipRepository {

    @Autowired
    JdbcTemplate template;

    /*public Ship fetchShipById(int id) {
        String sql = "SELECT * FROM ship WHERE ship_id = ?";
        RowMapper<Ship> rowMapper = new BeanPropertyRowMapper<>(Ship.class);
        return template.queryForObject(sql, rowMapper, id);
    }*/

    public Ship fetchShipById(int id) {
        String sql = "SELECT * FROM ship WHERE ship_id = ?";
        return template.queryForObject(sql, new ShipRowMapper(), id);
    }

    public List<Ship> fetchAllShips() {
        String sql = "SELECT * FROM ship";
        RowMapper<Ship> rowMapper = new BeanPropertyRowMapper<>(Ship.class);
        return template.query(sql, rowMapper);
    }

    public void createShip(Ship s) {
        String sql = "INSERT INTO ship VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, s.getShip_id(), s.getName(), s.getPosition().x, s.getPosition().y, s.getHullQuality(), s.getAmountOfGuns(), s.getAmountOfSailors(), s.getSailQuality(), s.getSpeed(), s.getDirection(), s.getPowerValue(), s.getAmmunition(), s.getTurns());
    }

    public void updateShip(Ship s) {
        String sql = "UPDATE ship SET name=?, positionX=?, positionY=?, hullQuality=?, amountOfGuns=?, amountOfSailors=?, sailQuality=?, speed=?, direction=?, powerValue=?, ammunition=?, turns=? WHERE ship_id=?";
        template.update(sql, s.getName(), s.getPosition().x, s.getPosition().y, s.getHullQuality(), s.getAmountOfGuns(), s.getAmountOfSailors(), s.getSailQuality(), s.getSpeed(), s.getDirection(), s.getPowerValue(), s.getAmmunition(), s.getTurns(), s.getShip_id());
    }

    public void deleteShip(int id) {
        String sql = "DELETE FROM ship WHERE ship_id = ?";
        template.update(sql, id);
    }

    public Ship fetchShipByName(String name) {
        String sql = "SELECT * FROM ship WHERE name = ? AND ship_id < 4";
        RowMapper<Ship> rowMapper = new BeanPropertyRowMapper<>(Ship.class);
        return template.queryForObject(sql, rowMapper, name);
    }

    public int findNewestShipId() {
        String sql = "SELECT LAST_INSERT_ID()";
        return template.queryForObject(sql, Integer.class);
    }

    /*List<Ship> ships = new ArrayList<>();

    {
        ships.add(new Ship(1, "Brig", null, 25.0, 8, 60, 4.0, 2, null, 8, null, 1));
        ships.add(new Ship(2, "Ship of the Line", null, 60.0, 16, 160, 10.0, 5, null, 16, null, 2));
        ships.add(new Ship(3, "Man at War", null, 140.0, 28, 340, 24.0, 4, null, 32, null, 1)) ;
    }

    public Ship fetchShipById(int id) {
        for (Ship ship : ships) {
            if (ship.getShip_id() == id) {
                return ship;
            }
        }
        System.out.println("Couldn't find a ship with that ID");
        return new Ship();
    }

    public List<Ship> fetchAllShips() {
        return ships;
    }

    public void updateShip(Ship s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).getShip_id().equals(s.getShip_id())) {
                ships.set(i, s);
            }
        }
    }*/
}
