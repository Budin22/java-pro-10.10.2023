package org.example;

import org.example.test.TestClass;
import org.example.test.TestRunner;

public class Main {
    public static void main(String[] args) {
        TestRunner testRunner = new TestRunner();
        testRunner.start(TestClass.class);
    }
}