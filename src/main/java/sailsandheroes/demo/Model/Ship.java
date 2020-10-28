package sailsandheroes.demo.Model;

import java.awt.*;
import java.util.List;
// Mona og Frederik
public class Ship {

    private Integer ship_id;
    private String name;
    private Point position;
    private double hullQuality;
    private Integer amountOfGuns;
    private Integer amountOfSailors;
    private double sailQuality;
    private Integer speed;
    private String direction;
    private int powerValue;
    private String ammunition;
    private Integer turns;
    private List<Point> path;

    public Ship() {
    }

    public Ship(Integer ship_id, String name, Point position, double hullQuality, Integer amountOfGuns, Integer amountOfSailors, double sailQuality,
                Integer speed, String direction, int powerValue, String ammunition, Integer turns) {
        this.ship_id = ship_id;
        this.name = name;
        this.position = position;
        this.hullQuality = hullQuality;
        this.amountOfGuns = amountOfGuns;
        this.amountOfSailors = amountOfSailors;
        this.sailQuality = sailQuality;
        this.speed = speed;
        this.direction = direction;
        this.powerValue = powerValue;
        this.ammunition = ammunition;
        this.turns = turns;
    }

    // William
    public void calculatePowerValue(){
        int guns = amountOfGuns / 2;
        int sailorPrGun = amountOfSailors/3;
        if(guns <= sailorPrGun){
            powerValue = guns;
        }else {
            powerValue = sailorPrGun;
        }
    }

    public Integer getShip_id() {
        return ship_id;
    }

    public void setShip_id(Integer ship_id) {
        this.ship_id = ship_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public double getHullQuality() {
        return hullQuality;
    }

    public void setHullQuality(double hullQuality) {
        this.hullQuality = hullQuality;
    }

    public Integer getAmountOfGuns() {
        return amountOfGuns;
    }

    public void setAmountOfGuns(Integer amountOfGuns) {
        this.amountOfGuns = amountOfGuns;
    }

    public Integer getAmountOfSailors() {
        return amountOfSailors;
    }

    public void setAmountOfSailors(Integer amountOfSailors) {
        this.amountOfSailors = amountOfSailors;
    }

    public double getSailQuality() {
        return sailQuality;
    }

    public void setSailQuality(double sailQuality) {
        this.sailQuality = sailQuality;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(int powerValue) {
        this.powerValue = powerValue;
    }

    public String getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(String ammunition) {
        this.ammunition = ammunition;
    }

    public Integer getTurns() {
        return turns;
    }

    public void setTurns(Integer turns) {
        this.turns = turns;
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "ship_id=" + ship_id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", hullQuality=" + hullQuality +
                ", amountOfGuns=" + amountOfGuns +
                ", amountOfSailors=" + amountOfSailors +
                ", sailQuality=" + sailQuality +
                ", speed=" + speed +
                ", direction='" + direction + '\'' +
                ", powerValue=" + powerValue +
                ", ammunition='" + ammunition + '\'' +
                ", turns=" + turns +
                ", path=" + path +
                '}';
    }
}
