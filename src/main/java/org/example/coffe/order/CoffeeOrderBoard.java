package org.example.coffe.order;

import java.util.HashMap;
import java.util.Map;

public class CoffeeOrderBoard implements OrderBoard<Order> {
    private int lastOrder = 0;
    private int firstOrder = 1;
    private final Map<Integer, Order> orders;
    public CoffeeOrderBoard(){
        orders = new HashMap<>();
    }

    public void addOrder(String name) {
        lastOrder++;
        Order order = new Order(lastOrder, name);
        orders.put(lastOrder, order);
    }

    public Order deliver() {
        if (lastOrder == 0) {
            System.out.println("We don't have any orders yet");
            return null;
        }
        Order order = orders.remove(firstOrder);
        while (order == null && firstOrder < lastOrder) {
            firstOrder++;
            order = orders.remove(firstOrder);
        }
        return order;
    }

    public Order deliver(int num) {
        Order order = orders.remove(num);
        if (order != null) {
            return order;
        } else {
            System.out.println("We don't have order with num: " + num);
            return null;
        }
    }

    public void printOrdersInLine() {
        System.out.println(orders.values());
    }

    public Map<Integer,Order> getOrders(){
        return orders;
    }
}
