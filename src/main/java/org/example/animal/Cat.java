package org.example.animal;

import org.example.animal.actions.Runnable;
import org.example.animal.actions.Swimable;

public class Cat extends Animal implements Swimable, Runnable {
    private static int catCount;
    public Cat(String name) {
        super(name);
        catCount++;
    }

    @Override
    public void run(int distance) {
        if(distance > 200){
            System.out.printf("%s can't run more than 200m \n", getName());
        } else {
            System.out.printf("%s run %dm \n", getName(), distance);
        }
    }

    @Override
    public void swim(int distance) {
            System.out.printf("%s can't swim \n", getName());
    }

    public static int getCatCount() {
        return catCount;
    }
}
