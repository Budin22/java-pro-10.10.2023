package org.example.model.mapper;

import org.example.model.Order;
import org.example.model.dto.OrderDto;
import org.example.model.dto.OrderWithProductsDto;

import java.util.LinkedList;
import java.util.List;

public class OrderMapperImp implements OrderMapper {
    @Override
    public OrderDto orderToOrderDto(Order order) {
        if (order != null) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setDate(order.getDate());
            orderDto.setTotalCost(order.getTotalCost());
            return orderDto;
        }
        return null;
    }

    @Override
    public OrderWithProductsDto orderToOrderWithProducts(Order order) {
        if (order != null) {
            OrderWithProductsDto orderWithProductsDto = new OrderWithProductsDto();
            orderWithProductsDto.setId(order.getId());
            orderWithProductsDto.setDate(order.getDate());
            orderWithProductsDto.setTotalCost(order.getTotalCost());
            orderWithProductsDto.setProducts(order.getProducts());
            return orderWithProductsDto;
        }
        return null;
    }

    @Override
    public List<OrderDto> orderListToOrderDtoList(List<Order> orders) {
        List<OrderDto> ordersDto = new LinkedList<>();
        if (orders != null) {
            orders.forEach(order -> ordersDto.add(orderToOrderDto(order)));
        }
        return ordersDto;
    }
}
