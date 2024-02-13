package org.example.service;

import org.example.model.dto.OrderDto;
import org.example.model.Order;
import org.example.model.dto.OrderWithProductsDto;
import org.glassfish.jersey.spi.Contract;

import java.util.List;


@Contract
public interface OrderService {
    OrderWithProductsDto getOrderById(int id);

    List<OrderDto> getAllOrders();

    OrderWithProductsDto addOrder(Order order);
}
