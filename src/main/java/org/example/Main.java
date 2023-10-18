package org.example;


import org.example.animal.Animal;
import org.example.animal.Cat;
import org.example.animal.Dog;

public class Main {
    public static void main(String[] args) {

        Dog bobbik1 = new Dog("Bobik1");
        Dog bobbik2 = new Dog("Bobik2");
        Dog bobbik3 = new Dog("Bobik3");

        Cat shurm1 = new Cat("Shurm1");
        Cat shurm2 = new Cat("Shurm2");


        System.out.printf("Dogs created : %d times \n", Dog.getDogCount());
        System.out.printf("Cats created : %d times \n", Cat.getCatCount());
        System.out.printf("Animals created : %d times \n", Animal.getAnimalCount());

        shurm1.run(50);
        shurm1.swim(12);
        shurm2.run(225);

        bobbik1.run(450);
        bobbik2.run(501);

        bobbik1.swim(25);
        bobbik2.swim(8);

    }
}