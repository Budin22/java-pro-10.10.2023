package org.goals.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/level")
public class LoggerController {
    private static final Logger logger = LogManager.getLogger(LoggerController.class);

    @GetMapping("/{level}")
    public String setLevel(@PathVariable("level") String level) {
        Level levelToSet = Level.getLevel(level);
        if (levelToSet != null) {
            Configurator.setRootLevel(levelToSet);
            logger.info("Change logger level");
            logger.debug("Change logger level to level: {}", levelToSet);
            return "You set level: " + levelToSet;
        }
        logger.info("Failed to change logger level");
        logger.debug("Failed to change logger level to level: {}", level);
        return "Failed to change logger level to level";
    }
}
