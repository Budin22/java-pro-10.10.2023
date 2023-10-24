package org.example.figure;

public class Rectangle implements AbleToCalculateArea {
    private final Double length;
    private final Double width;

    public Rectangle(Double length, Double width){
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }
}
