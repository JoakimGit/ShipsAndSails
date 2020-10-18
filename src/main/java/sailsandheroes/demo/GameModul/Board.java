package sailsandheroes.demo.GameModul;

import sailsandheroes.demo.Model.Hex;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
// Jakob
public class Board {

    private List<Hex> hexGrid;
    private int sizeX;
    private int sizeY;

    public Board() {
    }

    public List<Hex> getHexGrid() {
        return hexGrid;
    }

    public void setHexGrid(List<Hex> hexGrid) {
        this.hexGrid = hexGrid;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public void fillBoard(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.hexGrid = new ArrayList<>();
        for(int y = 0; y < sizeY; y++){
            for(int x = 0; x < sizeX; x++){
                Hex hex = new Hex(new Point(x, y));

                if ((x % 2 == 0 && y == 0) || ((x == 1 || x%2 == 1) && y == sizeY-1) || ((x == sizeX-1 && y == 0) || (x == 0 && y == sizeY-1))) {
                    hex.setEdge(true);
                }
                hexGrid.add(hex);
            }
        }
        coupleTogether(hexGrid);
    }

    private void coupleTogether(List<Hex> hexGrid) {
        for (Hex hex: hexGrid) {
            //hvis ulige y
            if(hex.getPosition().getY() % 2 != 0){
                coupleHex(hexGrid, hex);
            }//hvis y er lige
            else if(hex.getPosition().getY() % 2 == 0 || hex.getPosition().getY() == 0){
                coupleHex(hexGrid, hex);
            }
        }
    }

    private void coupleHex(List<Hex> hexGrid, Hex hex) {
        // hvis lige x
        if (hex.getPosition().getX() % 2 == 0) {
            for (Hex value : hexGrid) {
                if (value.getPosition().getX() == hex.getPosition().getX() - 1 && value.getPosition().getY() == hex.getPosition().getY() - 1) {
                    //hvis det passer med NW
                    hex.setnW(value);
                } else if (value.getPosition().getX() == hex.getPosition().getX() && value.getPosition().getY() == hex.getPosition().getY() - 1) {
                    //hvis det passer med N
                    hex.setN(value);
                } else if (value.getPosition().getX() == hex.getPosition().getX() + 1 && value.getPosition().getY() == hex.getPosition().getY() - 1) {
                    //hvis det passer med NE
                    hex.setnE(value);
                } else if (value.getPosition().getX() == hex.getPosition().getX() + 1 && value.getPosition().getY() == hex.getPosition().getY()) {
                    //hvis det passer med SE
                    hex.setsE(value);
                } else if (value.getPosition().getX() == hex.getPosition().getX() && value.getPosition().getY() == hex.getPosition().getY() + 1) {
                    //hvis det passer med S
                    hex.setS(value);
                } else if (value.getPosition().getX() == hex.getPosition().getX() - 1 && value.getPosition().getY() == hex.getPosition().getY()) {
                    //hvis det passer med SW
                    hex.setsW(value);
                }
            }
        }
        // hvis ulige x
        else if (hex.getPosition().getX() % 2 != 0) {
            for (Hex value : hexGrid) {
                if (value.getPosition().getX() == hex.getPosition().getX() - 1 && value.getPosition().getY() == hex.getPosition().getY()) {
                    //hvis det passer med NW
                    hex.setnW(value);
                } else if (value.getPosition().getX() == hex.getPosition().getX() && value.getPosition().getY() == hex.getPosition().getY() - 1) {
                    //hvis det passer med N
                    hex.setN(value);
                } else if (value.getPosition().getX() == hex.getPosition().getX() + 1 && value.getPosition().getY() == hex.getPosition().getY()) {
                    //hvis det passer med NE
                    hex.setnE(value);
                } else if (value.getPosition().getX() == hex.getPosition().getX() + 1 && value.getPosition().getY() == hex.getPosition().getY() + 1) {
                    //hvis det passer med SE
                    hex.setsE(value);
                } else if (value.getPosition().getX() == hex.getPosition().getX() && value.getPosition().getY() == hex.getPosition().getY() + 1) {
                    //hvis det passer med S
                    hex.setS(value);
                } else if (value.getPosition().getX() == hex.getPosition().getX() - 1 && value.getPosition().getY() == hex.getPosition().getY() + 1) {
                    //hvis det passer med SW
                    hex.setsW(value);
                }
            }
        }
    }
}
