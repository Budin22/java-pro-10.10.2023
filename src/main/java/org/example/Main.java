package org.example;

public class Main {
    public static void main(String[] args) {

        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println("(5+9) in diapason from 10 to 20: " + checkSumInDiapason(5,9));

        checkSign(-1);
    }

    private static void checkSign(int a){
        System.out.printf(a < 0 ? "%d: negative %n" : "%d: positive %n", a);
    }

    private static boolean checkSumInDiapason(int a, int b){
        return a+b >= 10 && a+b <= 20;
    }

    private static void compareNumbers(){
        int a = 8;
        int b = 12;
        System.out.printf(a >= b ? "%d >= %d %n" : "%d < %d %n", a,b);
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