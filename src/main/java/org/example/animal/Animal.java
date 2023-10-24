package org.example.animal;

public class Animal {
    private String name;
    private static int animalCount;
    public Animal(String name){
        this.name = name;
        animalCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getAnimalCount(){
        return animalCount;
    }
}
