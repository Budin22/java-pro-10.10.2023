package org.example.figure;

public class Circle implements AbleToCalculateArea {
    private final double radius;
    public Circle(double radius){
        this.radius = radius;
    }
    @Override
    public double getArea() {
        return Math.PI*radius*radius;
    }
}
