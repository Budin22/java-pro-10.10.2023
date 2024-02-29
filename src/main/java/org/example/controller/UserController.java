package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.dto.UserDto;
import org.example.model.dto.UserWithTasksDto;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping(value = {"", "/"})
    @ResponseBody
    public ResponseEntity<List<UserDto>> getAll() {
        logger.info("Make Get request getAllUser");
        List<UserDto> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserWithTasksDto> getUserById(@PathVariable("id") int id) {
        logger.info("Make Get request getUserById");
        logger.debug("Make Get request getUserById with id: {}", id);
        UserWithTasksDto user = userService.getUserById(id);
        if (user == null) {
            logger.error("Don't have user with id: {}", id);
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/")
    public ResponseEntity<UserWithTasksDto> addUser(UserWithTasksDto userWithTasksDto) {
        logger.info("Make Post request addUser");
        logger.debug("Make Post request addUser with userWithTasksDto: {}", userWithTasksDto);
        UserWithTasksDto addedUser = userService.saveUser(userWithTasksDto);
        return ResponseEntity.ok(addedUser);
    }

    @PutMapping("/")
    public ResponseEntity<UserWithTasksDto> updateUser(UserWithTasksDto userWithTasksDto) {
        logger.info("Make Put request updateUser");
        logger.debug("Make Put request updateUser with userWithTasksDto: {}", userWithTasksDto);
        UserWithTasksDto updatedUser = userService.saveUser(userWithTasksDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/")
    public ResponseEntity<UserWithTasksDto> removeUser(UserWithTasksDto userWithTasksDto) {
        logger.info("Make Delete request removeUser");
        logger.debug("Make Delete request removeUser with userWithTasksDto: {}", userWithTasksDto);
        UserWithTasksDto removedUser = userService.removeUser(userWithTasksDto);
        return ResponseEntity.ok(removedUser);
    }
}
