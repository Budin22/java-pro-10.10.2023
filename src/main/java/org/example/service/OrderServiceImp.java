package org.example.service;

import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.model.Order;
import org.example.model.dto.OrderDto;
import org.example.model.mapper.OrderMapper;
import org.example.repo.OrderRepo;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceImp implements OrderService {
    @Inject
    private OrderRepo orderRepo;
    @Inject
    private OrderMapper mapper;

    @Override
    public Order getOrderById(int id) {
        return orderRepo.getOrderById(id);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepo.getAllOrders();
        return mapper.orderListToOrderDtoList(orders);
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepo.addOrder(order);
    }
}
