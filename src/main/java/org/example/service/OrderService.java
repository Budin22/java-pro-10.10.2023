package org.example.service;

import org.example.model.Order;
import org.example.model.dto.OrderDto;
import org.glassfish.jersey.spi.Contract;

import java.util.List;


@Contract
public interface OrderService {
    Order getOrderById(int id);

    List<OrderDto> getAllOrders();

    Order addOrder(Order order);
}
