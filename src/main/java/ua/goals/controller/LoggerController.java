package ua.goals.controller;

import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/level")
public class LoggerController {

    @GetMapping("/{level}")
    public String setLevel(@PathVariable("level") String level) {
        Logger logger = LoggerFactory.getLogger("org.springframework");
        ch.qos.logback.classic.Logger loggerImpl = (ch.qos.logback.classic.Logger) logger;
        loggerImpl.setLevel(Level.valueOf(level));
        log.info("Change log level to " + Level.valueOf(level));
        return "Change to " + Level.valueOf(level);
    }

    @GetMapping(value = {"", "/"})
    public String getLevel() {
        Logger logger = LoggerFactory.getLogger("org.springframework");
        ch.qos.logback.classic.Logger loggerImpl = (ch.qos.logback.classic.Logger) logger;
        return loggerImpl.getLevel().toString();
    }
}
