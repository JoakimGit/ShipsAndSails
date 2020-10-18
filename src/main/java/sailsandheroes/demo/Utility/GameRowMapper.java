package sailsandheroes.demo.Utility;

import org.springframework.jdbc.core.RowMapper;
import sailsandheroes.demo.GameModul.Board;
import sailsandheroes.demo.GameModul.Game;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRowMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet resultSet, int i) throws SQLException {

        Game game = new Game();
        game.setGame_id(resultSet.getInt("game_id"));
        int x = (resultSet.getInt("boardSizeX"));
        int y = (resultSet.getInt("boardSizeY"));
        game.setBoardSizeX(x);
        game.setBoardSizeY(y);
        game.setPlayer1_id(resultSet.getInt("player1_id"));
        game.setPlayer2_id(resultSet.getInt("player2_id"));
        Board board = new Board();
        board.fillBoard(x, y);
        game.setBoard(board);

        return game;
    }
}
