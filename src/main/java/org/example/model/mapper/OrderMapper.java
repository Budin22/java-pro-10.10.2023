package org.example.model.mapper;

import org.example.model.Order;
import org.example.model.dto.OrderDto;
import org.example.model.dto.OrderWithProductsDto;
import org.glassfish.jersey.spi.Contract;

import java.util.List;

@Contract
public interface OrderMapper {
    OrderDto orderToOrderDto(Order order);
    OrderWithProductsDto orderToOrderWithProducts(Order order);
    List<OrderDto> orderListToOrderDtoList(List<Order> orders);
}
