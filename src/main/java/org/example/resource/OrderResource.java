package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Order;
import org.example.model.dto.OrderDto;
import org.example.model.dto.OrderWithProductsDto;
import org.example.service.OrderService;

import java.util.List;

@Path("/orders")
public class OrderResource {
    @Inject
    private OrderService orderService;
    private static final Logger logger = LogManager.getLogger(OrderResource.class);


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrder() {
        try {
            logger.info("Make Get request getAllOrder");
            List<OrderDto> orders = orderService.getAllOrders();
            return Response.ok(orders).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(500, "Bad").build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") int id) {
        logger.info("Make Get request getOrderById");
        logger.debug("Make Get request getOrderById with id: {}", id);
        OrderWithProductsDto order = orderService.getOrderById(id);
        if(order == null) return Response.status(404, "Don't have order with id: " + id).build();
        return Response.ok(order).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(Order order) {
        logger.info("Make Post request addOrder");
        logger.debug("Make Post request addOrder with order: {}", order);
        OrderWithProductsDto addedOrder = orderService.addOrder(order);
        return Response.ok(addedOrder).build();
    }
}
