package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.UserDto;
import org.example.model.dto.UserWithTasksDto;
import org.example.model.entity.User;
import org.example.model.mapper.UserMapper;
import org.example.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepo.getAllUser();
        return userMapper.userListToUserDtoList(users);
    }

    @Override
    public UserWithTasksDto getUserById(int id) {
        User user = userRepo.getUserById(id);
        return userMapper.userToUserWithTasksDto(user);
    }

    @Override
    public UserWithTasksDto saveUser(UserWithTasksDto userWithTasksDto) {
        User user = userMapper.userWithTasksDtoToUser(userWithTasksDto);
        User addedUser = userRepo.saveUser(user);
        return userMapper.userToUserWithTasksDto(addedUser);
    }

    @Override
    public UserWithTasksDto removeUser(UserWithTasksDto userWithTasksDto) {
        User user = userMapper.userWithTasksDtoToUser(userWithTasksDto);
        User removedUser = userRepo.removeUser(user);
        return userMapper.userToUserWithTasksDto(removedUser);
    }
}
