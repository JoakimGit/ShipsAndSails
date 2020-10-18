package sailsandheroes.demo.GameModul;

import sailsandheroes.demo.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int game_id;
    private int boardSizeX;
    private int boardSizeY;
    private Player player1;
    private Player player2;

    private int player1_id;
    private int player2_id;
    private Board board;

    public Game(){
    }

    public Game(int boardSizeX, int boardSizeY, Player player1, Player player2) {
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.player1 = player1;
        this.player2 = player2;
        board = new Board();
        board.fillBoard(boardSizeX, boardSizeY);
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getBoardSizeX() {
        return boardSizeX;
    }

    public void setBoardSizeX(int boardSizeX) {
        this.boardSizeX = boardSizeX;
    }

    public int getBoardSizeY() {
        return boardSizeY;
    }

    public void setBoardSizeY(int boardSizeY) {
        this.boardSizeY = boardSizeY;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(int player1_id) {
        this.player1_id = player1_id;
    }

    public int getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(int player2_id) {
        this.player2_id = player2_id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "Game{" +
                "game_id=" + game_id +
                ", boardSizeX=" + boardSizeX +
                ", boardSizeY=" + boardSizeY +
                ", player1=" + player1 +
                ", player2=" + player2 +
                '}';
    }
}
