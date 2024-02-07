package org.example.repo;

import org.example.model.Order;
import org.glassfish.jersey.spi.Contract;

import java.util.List;

@Contract
public interface OrderRepo {
    Order getOrderById(int id);
    List<Order> getAllOrders();
    Order addOrder(Order order);

}
