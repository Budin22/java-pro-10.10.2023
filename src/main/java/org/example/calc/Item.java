package org.example.calc;

public class Item <K, V>{
    private final K name;
    private final V occurrence;
    public Item(K name, V occurrence){
        this.name = name;
        this.occurrence = occurrence;
    }

    @Override
    public String toString() {
        return "{" +
                "name: " + name +
                ", occurrence: " + occurrence +
                "}";
    }
}
