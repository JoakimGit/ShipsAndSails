package sailsandheroes.demo.Model;

import java.awt.*;
// Jakob og Joakim
public class Hex {

    private Point position;
    private Hex nW;
    private Hex n;
    private Hex nE;
    private Hex sE;
    private Hex s;
    private Hex sW;
    private boolean edge;

    public Hex() {
        position =  new Point(0,0);
    }

    public Hex(Point position) {
        this.position = position;
    }

    public Hex(Point position, Hex nW, Hex n, Hex nE, Hex sE, Hex s, Hex sW, boolean edge) {
        this.position = position;
        this.nW = nW;
        this.n = n;
        this.nE = nE;
        this.sE = sE;
        this.s = s;
        this.sW = sW;
        this.edge = edge;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Hex getnW() {
        return nW;
    }

    public void setnW(Hex nW) {
        this.nW = nW;
    }

    public Hex getN() {
        return n;
    }

    public void setN(Hex n) {
        this.n = n;
    }

    public Hex getnE() {
        return nE;
    }

    public void setnE(Hex nE) {
        this.nE = nE;
    }

    public Hex getsE() {
        return sE;
    }

    public void setsE(Hex sE) {
        this.sE = sE;
    }

    public Hex getS() {
        return s;
    }

    public void setS(Hex s) {
        this.s = s;
    }

    public Hex getsW() {
        return sW;
    }

    public void setsW(Hex sW) {
        this.sW = sW;
    }

    public boolean isEdge() {
        return edge;
    }

    public void setEdge(boolean edge) {
        this.edge = edge;
    }

    public Hex getNeighbor(String direction) {
        switch (direction) {
            case "North":
                return getN();
            case "South":
                return getS();
            case "Northwest":
                return getnW();
            case "Northeast":
                return getnE();
            case "Southwest":
                return getsW();
            case "Southeast":
                return getsE();
        }
        return new Hex();
    }

    @Override
    public String toString() {
        return "Hex{" +
                "position=" + position +
                ", edge=" + edge +
                '}';
    }
}
