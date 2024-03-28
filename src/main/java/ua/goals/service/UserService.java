package ua.goals.service;


import ua.goals.model.dto.UserDto;
import ua.goals.model.dto.UserWithTasksDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUser();

    UserWithTasksDto getUserById(int id);

    UserWithTasksDto saveUser(UserWithTasksDto user);

    void removeUser(UserWithTasksDto user);
}
