package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("Char repeated: " + findSymbolOccurrence("Hello milk food", 'l'));
        System.out.println("Word position: " + findWordPosition("Hello milk food", "mil"));
        System.out.println("String: 'My perfect life!' reverse : " + stringReverse("My perfect life!"));
        System.out.println("'Hello' is palindrome: " + isPalindrome("Hello"));
        System.out.println("'Appa' is palindrome: " + isPalindrome("Appa"));

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