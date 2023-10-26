package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println(findSymbolOccurance("Hello milk food", ' '));
    }

    private static int findSymbolOccurance(String str, char myChar){
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