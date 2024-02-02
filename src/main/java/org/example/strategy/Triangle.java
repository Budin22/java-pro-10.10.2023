package org.example.strategy;

public class Triangle implements SquareStrategy {
    private final double height;
    private final double width;

    public Triangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double getSquare() {
        return height * width * 1 / 2;
    }
}
