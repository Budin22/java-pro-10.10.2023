package org.example;

import org.example.car.Car;
import org.example.employee.Employee;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();

        Employee employee = new Employee("Bobik Bob", "dog", "bobikdog@bob.gav", "+385255555", 15);

        SameName sameName1 = new SameName("first");
        org.example.testClass.SameName sameName2 = new org.example.testClass.SameName("second");
    }
}