package org.example.test;

import org.example.annotation.AfterSuite;
import org.example.annotation.BeforeSuite;
import org.example.annotation.TestSuite;
import org.example.calculator.Calculator;

public class CalculatorTest {
    private Calculator calc;

    @TestSuite(priority = 5)
    public void addingTwoAddTwoEqualsFour() {
        int result = calc.add(2,2);
        int expect = 4;
        System.out.println("addingTwoAddTwoEqualsFour test pass: " + (result == expect));
    }

    @TestSuite(priority = 3)
    public void addingTwoAddTwoNotEqualsFive() {
        int result = calc.add(2,2);
        int expect = 5;
        System.out.println("addingTwoAddTwoNotEqualsFive test pass: " + (result != expect));
    }

    @TestSuite(priority = 1)
    public void minTwoMinTwoNotEqualsFive() {
        int result = calc.min(2,2);
        int expect = 5;
        System.out.println("minTwoMinTwoNotEqualsFive test pass: " + (result != expect));
    }

    @TestSuite
    public void minTwoMinTwoEqualsZero() {
        int result = calc.min(2,2);
        int expect = 0;
        System.out.println("minTwoMinTwoEqualsZero test pass: " + (result == expect));
    }

    @AfterSuite
    public void afterAllTests() {
        System.out.println("All tests done!");
    }

    @BeforeSuite
    public void beforeAllTests() {
        System.out.println("Before all tests");
        this.calc = new Calculator();
    }
}

