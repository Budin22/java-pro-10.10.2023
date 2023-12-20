package org.example.fruit;

import org.example.fruit.annotation.NotNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FruitBox<T extends Fruit> {
    private final List<T> fruits;
    private float boxWight;

    public FruitBox() {
        fruits = new LinkedList<>();
    }

    public void addFruit(@NotNull T fruit) throws NullPointerException {
        if (fruit != null) {
            boxWight = getBoxWight() + fruit.getWight();
            fruits.add(fruit);
        }
    }

    public void merge(@NotNull FruitBox<T> fruitBox) {
        if (fruitBox != null) {
            fruits.addAll(fruitBox.getFruits());
            boxWight = getBoxWight() + fruitBox.getBoxWight();
        }
    }

    public void addFruits(@NotNull Iterator<T> iteratorWithFruits) {
        if (iteratorWithFruits != null) {
            while (iteratorWithFruits.hasNext()) {
                T fruit = iteratorWithFruits.next();
                fruits.add(fruit);
                boxWight = getBoxWight() + fruit.getWight();
            }
        }
    }

    public boolean compare(@NotNull FruitBox<? extends Fruit> fruitBox) {
        return fruitBox != null && getBoxWight() == fruitBox.getBoxWight();
    }

    public List<T> getFruits() {
        return fruits;
    }

    public float getBoxWight() {
        return boxWight;
    }
}
