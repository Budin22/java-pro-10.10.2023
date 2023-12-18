package org.example.test;

import org.example.annotation.AfterSuite;
import org.example.annotation.BeforeSuite;
import org.example.annotation.Test;

public class TestClass {

    @BeforeSuite
    public void before() {
        System.out.println("Before");
    }

    @Test(priority = 1)
    public void test1() {
        System.out.println("Test 1");
    }

    @Test(priority = 12)
    public void test12() {
        System.out.println("Test 12");
    }

    @Test(priority = 0)
    public void test0() {
        System.out.println("Test 0");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("Test 3");
    }

    @AfterSuite
    public void after() {
        System.out.println("After");
    }
}
