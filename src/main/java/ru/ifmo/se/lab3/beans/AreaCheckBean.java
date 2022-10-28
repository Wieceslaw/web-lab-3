package ru.ifmo.se.lab3.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="areaCheck")
@SessionScoped
public class AreaCheckBean implements Serializable {
    // datetime delay x y r result
    private Double x;
    private Double y;
    private Double r;

    public AreaCheckBean() {
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public void makeHit() {
        // start timer
        // get x, y, r - validated values
        // check if hit
        // stop timer & create point object
        // save point object to repository
    }

    public boolean check(double x, double y, double r) {
        if (x > 0 && y > 0) {
            return Math.sqrt((x * x) + (y * y)) <= r;
        } else if (x <= 0 && y >= 0) {
            return x >= -r / 2 && y <= r;
        } else if (x <= 0 && y <= 0) {
            return y >= -2 * x - r;
        } else {
            return false;
        }
    }
}
