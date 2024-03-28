package ua.goals.model.mapper;


import org.mapstruct.Mapper;
import ua.goals.model.dto.UserDto;
import ua.goals.model.dto.UserWithTasksDto;
import ua.goals.model.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    User userWithTasksDtoToUser(UserWithTasksDto userWithTasksDto);

    UserWithTasksDto userToUserWithTasksDto(User user);

    UserDto userToUserDto(User user);

    List<UserDto> userListToUserDtoList(List<User> users);
}
