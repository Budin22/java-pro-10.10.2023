package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.dto.UserDto;
import org.example.model.dto.UserWithTasksDto;
import org.example.service.UserService;

import java.util.List;

@Path("/user")
public class UserResource {
    @Inject
    private UserService userService;
    private static final Logger logger = LogManager.getLogger(UserResource.class);


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser() {
        try {
            logger.info("Make Get request getAllUser");
            List<UserDto> users = userService.getAllUser();
            return Response.ok(users).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(500, "Bad").build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        logger.info("Make Get request getUserById");
        logger.debug("Make Get request getUserById with id: {}", id);
        UserWithTasksDto user = userService.getUserById(id);
        if (user == null) return Response.status(404, "Don't have user with id: " + id).build();
        return Response.ok(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(UserWithTasksDto userWithTasksDto) {
        logger.info("Make Post request addUser");
        logger.debug("Make Post request addUser with userWithTasksDto: {}", userWithTasksDto);
        UserWithTasksDto addedUser = userService.saveUser(userWithTasksDto);
        return Response.ok(addedUser).build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UserWithTasksDto userWithTasksDto) {
        logger.info("Make Put request updateUser");
        logger.debug("Make Put request updateUser with userWithTasksDto: {}", userWithTasksDto);
        UserWithTasksDto updatedUser = userService.saveUser(userWithTasksDto);
        return Response.ok(updatedUser).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(UserWithTasksDto userWithTasksDto) {
        logger.info("Make Delete request removeUser");
        logger.debug("Make Delete request removeUser with userWithTasksDto: {}", userWithTasksDto);
        UserWithTasksDto removedUser = userService.removeUser(userWithTasksDto);
        return Response.ok(removedUser).build();
    }
}
