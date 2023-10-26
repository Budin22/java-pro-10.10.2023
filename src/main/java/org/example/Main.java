package org.example;

public class Main {
    public static void main(String[] args) {

        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println("(5+9) in diapason from 10 to 20: " + checkSumInDiapason(5,9));
        printNumberSign(-1);
        System.out.println("5 is positive: " + checkIsPositiveNumber(5));
        printStringRepeatedly("Good job!!!", 5);

        // Actually 100 year also should be the leap year
        System.out.println("Is the leap year: " + isLeapYear(100));
    }
    private static boolean isLeapYear(int year){
        if(year % 400 == 0){
            return true;
        }
        if(year % 100 == 0){
            return false;
        }
        return year % 4 == 0;
    }

    private static void printStringRepeatedly(String str, int repeat){
        for (int i = 0; i < repeat; i++) {
            System.out.println(str);
        }
    }
    private static boolean checkIsPositiveNumber(int a){
        return a >= 0;
    }

    private static void printNumberSign(int a){
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