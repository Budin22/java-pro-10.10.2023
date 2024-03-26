package org.goals.model.mapper;

import org.goals.model.dto.UserDto;
import org.goals.model.dto.UserWithTasksDto;
import org.goals.model.entity.User;

import java.util.List;

public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    User userWithTasksDtoToUser(UserWithTasksDto userWithTasksDto);

    UserWithTasksDto userToUserWithTasksDto(User user);

    UserDto userToUserDto(User user);

    List<UserDto> userListToUserDtoList(List<User> users);
}
