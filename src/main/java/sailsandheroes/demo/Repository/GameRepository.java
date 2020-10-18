package sailsandheroes.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import sailsandheroes.demo.GameModul.Game;
import sailsandheroes.demo.Utility.GameRowMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class GameRepository {

    @Autowired
    JdbcTemplate template;

    /*public Game fetchGameById(int id) {
        String sql = "SELECT * FROM game WHERE game_id = ?";
        RowMapper<Game> rowMapper = new BeanPropertyRowMapper<>(Game.class);
        return template.queryForObject(sql, rowMapper, id);
    }*/

    public Game fetchGameById(int id) {
        String sql = "SELECT * FROM game WHERE game_id = ?";
        return template.queryForObject(sql, new GameRowMapper(), id);
    }

    public List<Game> fetchAllGames() {
        String sql = "SELECT * FROM game";
        RowMapper<Game> rowMapper = new BeanPropertyRowMapper<>(Game.class);
        return template.query(sql, rowMapper);
    }

    public int createGame(Game g) {
        String sql = "INSERT INTO game (boardSizeX, boardSizeY, player1_id, player2_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, g.getBoardSizeX());
            ps.setInt(2, g.getBoardSizeY());
            ps.setInt(3, g.getPlayer1().getPlayer_id());
            ps.setInt(4, g.getPlayer2().getPlayer_id());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void updateGame(Game g) {
        String sql = "UPDATE game SET boardSizeX=?, boardSizeY=? WHERE game_id=?";
        template.update(sql, g.getBoardSizeX(), g.getBoardSizeY(), g.getGame_id());
    }

    public void deleteGame(int id) {
        String sql = "DELETE FROM game WHERE game_id = ?";
        template.update(sql, id);
    }
}
