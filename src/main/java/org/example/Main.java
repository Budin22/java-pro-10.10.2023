package org.example;

public class Main {
    public static void main(String[] args) {

        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();

    }


    private static void compareNumbers(){
        int a = 8;
        int b = 12;
        System.out.printf(a >= b ? "%d >= %d" : "%d < %d", a,b);
    }

    private  static  void printColor(){
        int value = 0;
        if(value <= 0){
            System.out.println("Червоний");
            return;
        }
        if(value <= 100){
            System.out.println("Жовтий");
            return;
        }
        System.out.println("Зелений");
    }

    private static void printThreeWords(){
        System.out.println("""
                Apple
                Orange
                Banana
                """);
    }

    private static void checkSumSign(){
        int a = 8;
        int b = 12;
        System.out.println((a+b) >= 0 ? "Сума позитивна" : "Сума негативна");

    }

}