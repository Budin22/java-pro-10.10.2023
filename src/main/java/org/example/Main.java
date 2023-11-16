package org.example;

import org.example.calc.SomeCalc;
import org.example.phonebook.Contact;
import org.example.phonebook.PhoneBook;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SomeCalc someCalc = new SomeCalc();
        List<String> someList = new ArrayList<>();
        someList.add("target");
        someList.add("apple");
        someList.add("apple");
        someList.add("apple");
        someList.add("table");
        someList.add("can");
        someList.add("can");
        someList.add("bunny");
        someList.add("target");
        someList.add("target");
        someList.add("target");
        someCalc.countOccurrence(someList, "apple");

        String[] arr = new String[]{"target", "apple"};
        System.out.println("Cast array to list: " + someCalc.toList(arr));

        System.out.println("find unique: " + someCalc.findUnique(someList));
        System.out.println("find unique with set: " + someCalc.findUniqueWithSet(someList));
        someCalc.getOccurrence(someList).forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println(someCalc.findOccurrence(someList));

        System.out.println("\n------------------------next block--------------------\n");

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add(new Contact("sergii", "222"));
        phoneBook.add(new Contact("budin", "121231231"));
        phoneBook.add(new Contact("sergii", "111"));
        phoneBook.add(new Contact("sergii", "222"));
        phoneBook.add(new Contact("sergii", "333"));

        System.out.println("find budin22: " + phoneBook.find("budin22"));
        System.out.println("find sergii: " + phoneBook.find("sergii"));
        System.out.println("find all sergii: " + phoneBook.findAll("sergii"));
    }
}