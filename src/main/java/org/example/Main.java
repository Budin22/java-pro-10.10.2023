package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Char repeated: " + findSymbolOccurrence("Hello milk food", 'l'));
        System.out.println("Word position: " + findWordPosition("Hello milk food", "mil"));
        System.out.println("String: 'My perfect life!' reverse : " + stringReverse("My perfect life!"));
        System.out.println("'Hello' is palindrome: " + isPalindrome("Hello"));
        System.out.println("'Appa' is palindrome: " + isPalindrome("Appa"));

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado" , "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", " pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        guessWordGame(words);
    }

    private static void guessWordGame(String[] words){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("You need to guess one of this words: \n" + Arrays.toString(words));
        System.out.println("Or write 'exit' to stop game");

        String guessWord = words[(int) Math.floor(Math.random()*(words.length -1))];
//        System.out.println("The guess word is: " + guessWord);

        char[] result = new char[]{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'};
        String userGuesses = scanner.next();
        while (!userGuesses.equals("exit")){
            if(guessWord.equals(userGuesses)){
                System.out.println("You win the guess word was: " + guessWord);
                userGuesses = "exit";
            } else {
                for (int i = 0; i < userGuesses.length(); i++) {
                    if(guessWord.charAt(i) == userGuesses.charAt(i)){
                        result[i] = guessWord.charAt(i);
                    }
                }
                System.out.println(result);
                userGuesses = scanner.next();
            }
        }
        scanner.close();
    }

    private static boolean isPalindrome(String str){
        str = str.toLowerCase();
        return str.equals(stringReverse(str));
    }

    private static String stringReverse(String str){
        StringBuilder stringBuilder = new StringBuilder(str);
        return stringBuilder.reverse().toString();
    }
    private static int findWordPosition(String source, String target){
        return source.indexOf(target);
    }

    private static int findSymbolOccurrence(String str, char myChar){
        char[] charArray = str.toCharArray();
        int charRepeated = 0;
        for(char c : charArray){
            if(c == myChar){
                charRepeated++;
            }
        }
        return charRepeated;
    }
}