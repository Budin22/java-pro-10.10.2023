package org.example;

import org.example.fruit.Apple;
import org.example.fruit.FruitBox;
import org.example.fruit.Orange;

public class Main {
    public static void main(String[] args) {
        FruitBox<Apple> appleFruitBox = new FruitBox<>();
        FruitBox<Orange> orangeFruitBox = new FruitBox<>();

        Apple apple = new Apple(1.0F);
        Apple apple1 = new Apple(1.0F);
        Apple apple2 = new Apple(1.0F);

        Orange orange = new Orange(1.5F);
        Orange orange1 = new Orange(1.5F);

        orangeFruitBox.addFruit(orange);
        orangeFruitBox.addFruit(orange1);
        appleFruitBox.addFruit(apple);
        appleFruitBox.addFruit(apple1);
        appleFruitBox.addFruit(apple2);

        System.out.println("orange box wight: " + orangeFruitBox.getBoxWight());
        System.out.println("apple box wight: " + appleFruitBox.getBoxWight());
        System.out.println("orange equal apple: " + orangeFruitBox.compare(appleFruitBox));
    }
}