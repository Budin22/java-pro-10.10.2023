package org.example;

public class Main {
    public static void main(String[] args) {

        printThreeWords();
        checkSumSign();

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