package org.example.animal;

import org.example.animal.Animal;
import org.example.animal.actions.Runnable;
import org.example.animal.actions.Swimable;

public class Dog extends Animal implements Swimable, Runnable {
    private static int dogCount;
    public Dog(String name) {
        super(name);
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if(distance > 500){
            System.out.printf("%s can't run more than 500m \n", getName());
        } else {
            System.out.printf("%s run %dm \n", getName(), distance);
        }
    }

    @Override
    public void swim(int distance) {
        if(distance > 10){
            System.out.printf("%s can't swim more than 10m \n", getName());
        } else {
            System.out.printf("%s run %dm \n", getName(), distance);
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}
