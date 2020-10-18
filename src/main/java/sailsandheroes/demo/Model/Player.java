package sailsandheroes.demo.Model;

import java.util.List;
// Jakob
public class Player {

    private int player_id;
    private String playerName;
    private List<Ship> shipList;
    //private int game_id;

    public Player(){
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }

    /*public Player(String playerName, int game_id) {
        this.playerName = playerName;
        this.game_id = game_id;
    }*/

    public Player(List<Ship> shipList, int player_id, String playerName) {
        this.shipList = shipList;
        this.player_id = player_id;
        this.playerName = playerName;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public void setShip(List shipList) {
        this.shipList = shipList;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /*public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }*/

    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            Player player2 = (Player) o;
            return player_id == player2.player_id;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "player_id=" + player_id +
                ", playerName='" + playerName + '\'' +
                ", shipList=" + shipList +
                '}';
    }
}
