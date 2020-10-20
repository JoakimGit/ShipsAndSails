package sailsandheroes.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import sailsandheroes.demo.Model.Player;
import sailsandheroes.demo.Model.Ship;
import sailsandheroes.demo.Utility.ShipRowMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PlayerRepository {

    @Autowired
    JdbcTemplate template;

    public Player fetchPlayerById(int id) {
        String sql = "SELECT * FROM player WHERE player_id = ?";
        RowMapper<Player> rowMapper = new BeanPropertyRowMapper<>(Player.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public List<Player> fetchAllPlayers() {
        String sql = "SELECT * FROM player";
        RowMapper<Player> rowMapper = new BeanPropertyRowMapper<>(Player.class);
        return template.query(sql, rowMapper);
    }

    public int createPlayer(Player p) {
        String sql = "INSERT INTO player (playerName) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getPlayerName());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void updatePlayer(Player p) {
        String sql = "UPDATE player SET playerName=? WHERE player_id=?";
        template.update(sql, p.getPlayerName(), p.getPlayer_id());
    }

    public void deletePlayer(int id) {
        String sql = "DELETE FROM player WHERE player_id = ?";
        template.update(sql, id);
    }

    public void createPlayerShip(Player p, Ship s) {
        String sql = "INSERT INTO playerships VALUES (?, ?)";
        template.update(sql, p.getPlayer_id(), s.getShip_id());
    }

    public List<Ship> fetchShipsToPlayer(Player p) {
        String sql = "SELECT * FROM ship JOIN playerships ON playerships.ship_id = ship.ship_id WHERE player_id = ?";
        return template.query(sql, new ShipRowMapper(), p.getPlayer_id());
    }

    public int fetchPlayerIDByShipID(int id) {
        String sql = "SELECT player_id FROM playerships WHERE ship_id = ?";
        return template.queryForObject(sql, Integer.class, id);
    }

    public int findNewestPlayerId() {
        String sql = "SELECT LAST_INSERT_ID()";
        return template.queryForObject(sql, Integer.class);
    }
}
