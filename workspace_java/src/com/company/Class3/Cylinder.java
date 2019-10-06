package com.company.Class3;

public class Cylinder {
    private Circle circle;
    private double height;

    public Cylinder(Circle circle, double height) {
        this.circle = circle;
        this.height = height;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume(){
        return this.circle.getArea()*this.height;
    }

    @Override
    public String toString() {
        return "circle:"+circle;
    }
}
