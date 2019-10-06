package com.company.Class3;

public class TestCylinder {
    public static void main(String[] args) {
        Point point = new Point(1,1);
        Circle circle = new Circle(point,1);
        Cylinder cylinder = new Cylinder(circle,1);
        System.out.print("体积："+cylinder.getVolume());
    }
}
