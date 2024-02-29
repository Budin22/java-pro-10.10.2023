package org.example.service;

import org.example.model.dto.UserDto;
import org.example.model.dto.UserWithTasksDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUser();

    UserWithTasksDto getUserById(int id);

    UserWithTasksDto saveUser(UserWithTasksDto user);

    UserWithTasksDto removeUser(UserWithTasksDto user);
}
