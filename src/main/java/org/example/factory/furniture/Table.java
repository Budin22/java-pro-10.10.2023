package org.example.factory.furniture;

public class Table implements Furniture{
    @Override
    public void make() {
        System.out.println("Table made");
    }
}
