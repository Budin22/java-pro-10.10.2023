package org.goals.service;

import org.goals.model.dto.UserDto;
import org.goals.model.dto.UserWithTasksDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUser();

    UserWithTasksDto getUserById(int id);

    UserWithTasksDto saveUser(UserWithTasksDto user);

    UserWithTasksDto removeUser(UserWithTasksDto user);
}
