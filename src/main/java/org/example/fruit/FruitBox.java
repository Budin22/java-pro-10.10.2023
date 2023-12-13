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
        if (fruit == null) throw new NullPointerException();
        boxWight = getBoxWight() + fruit.getWight();
        fruits.add(fruit);
    }

    public void merge(@NotNull FruitBox<T> fruitBox) throws NullPointerException {
        if (fruitBox == null) throw new NullPointerException();
        fruits.addAll(fruitBox.getFruits());
        boxWight = getBoxWight() + fruitBox.getBoxWight();
    }

    public void addFruits(@NotNull Iterator<T> iteratorWithFruits) throws NullPointerException {
        if (iteratorWithFruits == null) throw new NullPointerException();
        while (iteratorWithFruits.hasNext()) {
            T fruit = iteratorWithFruits.next();
            fruits.add(fruit);
            boxWight = getBoxWight() + fruit.getWight();
        }
    }

    public boolean compare(@NotNull FruitBox<? extends Fruit> fruitBox) throws NullPointerException {
        if (fruitBox == null) throw new NullPointerException();
        return getBoxWight() == fruitBox.getBoxWight();
    }

    public List<T> getFruits() {
        return fruits;
    }

    public float getBoxWight() {
        return boxWight;
    }
}
