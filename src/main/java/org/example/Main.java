package org.example;

import org.example.navigator.FileNavigator;

public class Main {
    public static void main(String[] args) {
        FileNavigator fileNavigator = new FileNavigator();

        fileNavigator.addFile("C:\\Users\\Alena\\Desktop\\Lesson\\java-pro-10.10.2023\\src\\main\\java\\org\\example\\FileData.java");
        fileNavigator.addFilesFromDirectory("C:\\Users\\Alena\\Desktop\\image");
        fileNavigator.addFile("src/main/java/org/example/FileData.java");

        System.out.println("get all files: " + fileNavigator.getAllFiles());
        System.out.println("sorted by size: " + fileNavigator.getSortedBySize());
        System.out.println("filter by size 2000: " + fileNavigator.filterBySize(2000));
        System.out.println("find by path: " + fileNavigator.find("src/main/java/org/example/"));
        System.out.println("find by abs path: " + fileNavigator.find("C:\\Users\\Alena\\Desktop\\Lesson\\java-pro-10.10.2023\\src\\main\\java\\org\\example\\"));
        fileNavigator.remove("src/main/java/org/example/");
        System.out.println("find by path: " + fileNavigator.find("src/main/java/org/example/"));
        System.out.println("find by path: " + fileNavigator.find("C:\\Users\\Alena\\Desktop\\Lesson\\java-pro-10.10.2023\\src\\main\\java\\org\\example\\"));
    }
}