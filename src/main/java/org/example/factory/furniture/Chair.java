package org.example.factory.furniture;

public class Chair implements Furniture{
    @Override
    public void make() {
        System.out.println("Chair made");
    }
}
