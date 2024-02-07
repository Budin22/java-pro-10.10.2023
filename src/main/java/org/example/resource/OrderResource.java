package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.model.Order;
import org.example.model.dto.OrderDto;
import org.example.service.OrderService;

import java.util.List;

@Path("/orders")
public class OrderResource {
    @Inject
    private OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        System.out.println("Good");
        List<OrderDto> orders = orderService.getAllOrders();
        return Response.ok(orders).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Order order = orderService.getOrderById(id);
        return Response.ok(order).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBoard(Order order) {
        Order addedOrder = orderService.addOrder(order);
        Order fullOrder = orderService.getOrderById(addedOrder.getId());
        return Response.ok(fullOrder).build();
    }
}
