package org.example.figure;

public class Triangle implements AbleToCalculateArea {
    private final Double a;
    private final Double b;
    private final Double c;

    public Triangle(Double a, Double b, Double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        double p = (a+b+c)*0.5;
        return Math.pow((p*(p-a)*(p-b)*(p-c)), 0.5);
    }
}
