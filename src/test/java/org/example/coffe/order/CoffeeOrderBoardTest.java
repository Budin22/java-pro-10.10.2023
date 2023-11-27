package org.example.coffe.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CoffeeOrderBoardTest {

    private CoffeeOrderBoard orderBoard;

    @BeforeEach
    void init() {
        orderBoard = new CoffeeOrderBoard();
    }

    @Test
    void addOrder() {
        String name1 = "Sergii";
        String name2 = "Pavel";

        orderBoard.addOrder(name1);
        orderBoard.addOrder(name2);

        Map<Integer, Order> orders = orderBoard.getOrders();
        assertEquals(2, orders.size(), "Map size should have to Order");
        assertEquals(name1, orders.get(1).name(), "Value for '1' should be " + name1);
        assertEquals(name2, orders.get(2).name(), "Value for '2' should be " + name2);
    }

    @Test
    void deliver() {
        String name1 = "Sergii";
        String name2 = "Pavel";

        orderBoard.addOrder(name1);
        orderBoard.addOrder(name2);

        Order order1 = orderBoard.deliver();
        Order order2 = orderBoard.deliver();
        assertEquals(name1, order1.name(), "Value for order name should be " + name1);
        assertEquals(name2, order2.name(), "Value for order name should be " + name2);
    }

    @Test
    void deliverShouldBeNullWithNoOrders() {
        Order order = orderBoard.deliver();
        assertNull(order, "Value for order should be null with no orders");
    }

    @Test
    void deliverShouldBeNullWithNoSuchOrderNumber() {
        Order order = orderBoard.deliver(1);
        assertNull(order, "Value for order should be null with no such order number");
    }

    @Test
    void deliverByOrderNumber() {
        String name1 = "Sergii";
        String name2 = "Pavel";

        orderBoard.addOrder(name1);
        orderBoard.addOrder(name2);
        Order order1 = orderBoard.deliver(1);
        Order order2 = orderBoard.deliver(2);
        assertEquals(1, order1.number(), "Value for order 1 should be " + order1.number());
        assertEquals(2, order2.number(), "Value for order 2 should be " + order2.number());
    }


}