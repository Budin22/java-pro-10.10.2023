package org.example.model.mapper;

import jakarta.inject.Inject;
import org.example.model.dto.TaskDto;
import org.example.model.dto.UserDto;
import org.example.model.dto.UserWithTasksDto;
import org.example.model.entity.Task;
import org.example.model.entity.User;

import java.util.LinkedList;
import java.util.List;

public class UserMapperImp implements UserMapper {
    @Inject
    private TaskMapper taskMapper;

    @Override
    public User userDtoToUser(UserDto userDto) {
        if (userDto != null) {
            User user = new User();
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            return user;
        }
        return null;
    }

    @Override
    public User userWithTasksDtoToUser(UserWithTasksDto userWithTasksDto) {
        if (userWithTasksDto != null) {
            User user = new User();
            user.setId(userWithTasksDto.getId());
            user.setName(userWithTasksDto.getName());
            List<Task> tasks = taskMapper.taskDtoListToTaskList(userWithTasksDto.getTasks());
            user.setTasks(tasks);
            return user;
        }
        return null;
    }

    @Override
    public UserWithTasksDto userToUserWithTasksDto(User user) {
        if (user != null) {
            UserWithTasksDto userWithTasksDto = new UserWithTasksDto();
            userWithTasksDto.setId(user.getId());
            userWithTasksDto.setName(user.getName());
            List<TaskDto> taskDtoList = taskMapper.taskListToTaskDtoList(user.getTasks());
            userWithTasksDto.setTasks(taskDtoList);
            return userWithTasksDto;
        }
        return null;
    }

    @Override
    public UserDto userToUserDto(User user) {
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            return userDto;
        }
        return null;
    }

    @Override
    public List<UserDto> userListToUserDtoList(List<User> users) {
        List<UserDto> userDtoList = new LinkedList<>();
        if (users != null) {
            users.forEach(user -> {
                UserDto userDto = userToUserDto(user);
                userDtoList.add(userDto);
            });
        }
        return userDtoList;
    }
}
