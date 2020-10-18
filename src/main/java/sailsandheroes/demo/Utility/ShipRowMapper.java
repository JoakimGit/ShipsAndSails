package sailsandheroes.demo.Utility;

import org.springframework.jdbc.core.RowMapper;
import sailsandheroes.demo.Model.Ship;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipRowMapper implements RowMapper<Ship> {

    @Override
    public Ship mapRow(ResultSet resultSet, int i) throws SQLException {

        Ship ship = new Ship();
        ship.setShip_id(resultSet.getInt("ship_id"));
        ship.setName(resultSet.getString("name"));
        ship.setPosition(new Point(resultSet.getInt("positionX"), resultSet.getInt("positionY")));
        ship.setHullQuality(resultSet.getFloat("hullQuality"));
        ship.setAmountOfGuns(resultSet.getInt("amountOfGuns"));
        ship.setAmountOfSailors(resultSet.getInt("amountOfSailors"));
        ship.setSailQuality(resultSet.getFloat("sailQuality"));
        ship.setSpeed(resultSet.getInt("speed"));
        ship.setDirection(resultSet.getString("direction"));
        ship.setPowerValue(resultSet.getInt("powerValue"));
        ship.setAmmunition(resultSet.getString("ammunition"));
        ship.setTurns(resultSet.getInt("turns"));

        return ship;
    }
}
