package org.example.strategy;

public class Rectangle implements Strategy {
    private final double height;
    private final double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double getSquare() {
        return height * width;
    }
}
