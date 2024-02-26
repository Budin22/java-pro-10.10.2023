package org.example.model.mapper;

import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.dto.TaskDto;
import org.example.model.dto.UserDto;
import org.example.model.dto.UserWithTasksDto;
import org.example.model.entity.Task;
import org.example.model.entity.User;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserMapperImp implements UserMapper {
    @Inject
    private TaskMapper taskMapper;
    private static final Logger logger = LogManager.getLogger(UserMapperImp.class);

    @Override
    public User userDtoToUser(UserDto userDto) {
        logger.debug("Method userDtoToUser received userDto: {}", userDto);
        User user = null;
        if (userDto != null) {
            user = new User();
            user.setId(userDto.getId());
            user.setName(userDto.getName());
        }
        logger.debug("Method userDtoToUser return user: {}", user);
        return user;
    }

    @Override
    public User userWithTasksDtoToUser(UserWithTasksDto userWithTasksDto) {
        logger.debug("Method userWithTasksDtoToUser received userWithTasksDto: {}", userWithTasksDto);
        User user = null;
        if (userWithTasksDto != null) {
            user = new User();
            user.setId(userWithTasksDto.getId());
            user.setName(userWithTasksDto.getName());
            List<Task> tasks = taskMapper.taskDtoListToTaskList(userWithTasksDto.getTasks());
            user.setTasks(tasks);
        }
        logger.debug("Method userWithTasksDtoToUser return user: {}", user);
        return user;
    }

    @Override
    public UserWithTasksDto userToUserWithTasksDto(User user) {
        logger.debug("Method userToUserWithTasksDto received user: {}", user);
        UserWithTasksDto userWithTasksDto = null;
        if (user != null) {
            userWithTasksDto = new UserWithTasksDto();
            userWithTasksDto.setId(user.getId());
            userWithTasksDto.setName(user.getName());
            List<TaskDto> taskDtoList = taskMapper.taskListToTaskDtoList(user.getTasks());
            userWithTasksDto.setTasks(taskDtoList);
        }
        logger.debug("Method userToUserWithTasksDto return userWithTasksDto: {}", userWithTasksDto);
        return userWithTasksDto;
    }

    @Override
    public UserDto userToUserDto(User user) {
        logger.debug("Method userDtoToUser received user: {}", user);
        UserDto userDto = null;
        if (user != null) {
            userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
        }
        logger.debug("Method userDtoToUser return userDto: {}", userDto);
        return userDto;
    }

    @Override
    public List<UserDto> userListToUserDtoList(List<User> users) {
        logger.debug("Method userListToUserDtoList received users: {}", users);
        List<UserDto> userDtoList = new LinkedList<>();
        if (users != null) {
            users.forEach(user -> {
                UserDto userDto = userToUserDto(user);
                userDtoList.add(userDto);
            });
        }
        logger.debug("Method userListToUserDtoList return userDtoList: {}", userDtoList);
        return userDtoList;
    }
}
