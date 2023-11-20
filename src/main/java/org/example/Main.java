package org.example;

public class Main {
    public static void main(String[] args) {

        FileNavigator fileNavigator = new FileNavigator();

//        fileNavigator.run("src/main/java/org/example");
//        fileNavigator.run("src/main/java/org/example");
        fileNavigator.add("C:\\Users\\Alena\\Desktop\\Lesson\\java-pro-10.10.2023\\src\\main\\java\\org\\example\\FileData.java");
        fileNavigator.add("src/main/java/org/example/FileData.java");

        System.out.println("get all files: " + fileNavigator.getAllFiles());
        System.out.println("sorted by size: " + fileNavigator.getSortedBySize());
        System.out.println("filter by size 2540: " + fileNavigator.filterBySize(2000));
        System.out.println("find by path: " + fileNavigator.find("src/main/java/org/example/FileData.java"));
        System.out.println("find by abs path: " + fileNavigator.find("C:\\Users\\Alena\\Desktop\\Lesson\\java-pro-10.10.2023\\src\\main\\java\\org\\example\\FileData.java"));

        fileNavigator.remove("src/main/java/org/example/FileData.java");
        System.out.println("find by path: " + fileNavigator.find("src/main/java/org/example/FileData.java"));

        System.out.println("find by path: " + fileNavigator.find("C:\\Users\\Alena\\Desktop\\Lesson\\java-pro-10.10.2023\\src\\main\\java\\org\\example\\FileData.java"));

    }
}