package org.example;

import org.example.coffe.order.CoffeeOrderBoard;

public class Main {
    public static void main(String[] args) {
        CoffeeOrderBoard orderBoard = new CoffeeOrderBoard();

        orderBoard.deliver();
        for (int i = 1; i < 15; i++) {
            orderBoard.addOrder("order#" + i);
        }
        System.out.println("Get order in line: " + orderBoard.deliver());
        System.out.println("Get order by number 4: " + orderBoard.deliver(4));
        System.out.println("Get order by number 5: " + orderBoard.deliver(5));
        System.out.println("Get order by number 5: " + orderBoard.deliver(5));
        System.out.println("Get order by number 12: " + orderBoard.deliver(12));
        System.out.println("Get order by number 1: " + orderBoard.deliver(1));
        System.out.println("Get order by number 14: " + orderBoard.deliver(14));
        System.out.println("Get order in line: " + orderBoard.deliver());
        System.out.println("Get order in line: " + orderBoard.deliver());

        orderBoard.printOrdersInLine();
    }
}