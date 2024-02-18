package org.example.model.mapper;

import org.example.model.dto.UserDto;
import org.example.model.dto.UserWithTasksDto;
import org.example.model.entity.User;
import org.glassfish.jersey.spi.Contract;

import java.util.List;

@Contract
public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    User userWithTasksDtoToUser(UserWithTasksDto userWithTasksDto);

    UserWithTasksDto userToUserWithTasksDto(User user);

    UserDto userToUserDto(User user);

    List<UserDto> userListToUserDtoList(List<User> users);
}