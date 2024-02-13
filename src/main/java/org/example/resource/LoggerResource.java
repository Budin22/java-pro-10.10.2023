package org.example.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

@Path("/level")
public class LoggerResource {
    private static final Logger logger = LogManager.getLogger(LoggerResource.class);

    @GET
    @Path("{level}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeLoggerLevel(@PathParam("level") String level) {
        Level levelToSet = Level.getLevel(level);
        if (levelToSet != null) {
            Configurator.setRootLevel(levelToSet);
            logger.info("Change logger level");
            logger.debug("Change logger level to level: {}", levelToSet);
            return Response.ok("You set level: " + levelToSet).build();
        }
        logger.info("Failed to change logger level");
        logger.debug("Failed to change logger level to level: {}", level);
        return Response.status(400, "You should have valid level but send: " + level).build();
    }
}
