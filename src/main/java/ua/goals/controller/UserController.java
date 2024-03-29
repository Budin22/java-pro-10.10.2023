package ua.goals.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.goals.model.dto.UserDto;
import ua.goals.model.dto.UserWithTasksDto;
import ua.goals.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = {"", "/"})
    @ResponseBody
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserWithTasksDto> getUserById(@PathVariable("id") int id) {
        UserWithTasksDto user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<UserWithTasksDto> addUser(@RequestBody UserWithTasksDto userWithTasksDto) {
        UserWithTasksDto addedUser = userService.saveUser(userWithTasksDto);
        return ResponseEntity.ok(addedUser);
    }

    @PutMapping(value = {"", "/"})
    public ResponseEntity<UserWithTasksDto> updateUser(@RequestBody UserWithTasksDto userWithTasksDto) {
        UserWithTasksDto updatedUser = userService.saveUser(userWithTasksDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = {"", "/"})
    public ResponseEntity<String> removeUser(@RequestBody UserWithTasksDto userWithTasksDto) {
        userService.removeUser(userWithTasksDto);
        return ResponseEntity.ok("User deleted");
    }
}
