package org.example;

import org.example.test.TestedClass;
import org.example.test.TestRunner;

public class Main {
    public static void main(String[] args) {
        TestRunner testRunner = new TestRunner();
        testRunner.start(TestedClass.class);
    }
}