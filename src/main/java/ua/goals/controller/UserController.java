package ua.goals.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.goals.model.dto.UserDto;
import ua.goals.model.dto.UserWithTasksDto;
import ua.goals.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = {"", "/"})
    @ResponseBody
    public ResponseEntity<List<UserDto>> getAll() {
        log.info("Make Get request getAllUser");
        List<UserDto> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserWithTasksDto> getUserById(@PathVariable("id") int id) {
        log.info("Make Get request getUserById");
        log.debug("Make Get request getUserById with id: {}", id);
        UserWithTasksDto user = userService.getUserById(id);
        if (user == null) {
            log.error("Don't have user with id: {}", id);
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<UserWithTasksDto> addUser(@RequestBody UserWithTasksDto userWithTasksDto) {
        log.info("Make Post request addUser");
        log.debug("Make Post request addUser with userWithTasksDto: {}", userWithTasksDto);
        UserWithTasksDto addedUser = userService.saveUser(userWithTasksDto);
        return ResponseEntity.ok(addedUser);
    }

    @PutMapping(value = {"", "/"})
    public ResponseEntity<UserWithTasksDto> updateUser(@RequestBody UserWithTasksDto userWithTasksDto) {
        log.info("Make Put request updateUser");
        log.debug("Make Put request updateUser with userWithTasksDto: {}", userWithTasksDto);
        UserWithTasksDto updatedUser = userService.saveUser(userWithTasksDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = {"", "/"})
    public ResponseEntity<String> removeUser(@RequestBody UserWithTasksDto userWithTasksDto) {
        log.info("Make Delete request removeUser");
        log.debug("Make Delete request removeUser with userWithTasksDto: {}", userWithTasksDto);
        userService.removeUser(userWithTasksDto);
        return ResponseEntity.ok("User deleted");
    }
}
