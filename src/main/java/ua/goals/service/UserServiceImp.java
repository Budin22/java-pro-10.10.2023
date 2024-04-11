package ua.goals.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goals.model.dto.UserDto;
import ua.goals.model.dto.UserWithTasksDto;
import ua.goals.model.entity.User;
import ua.goals.model.mapper.UserMapper;
import ua.goals.repo.UserJpaRepo;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserJpaRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepo.findAll();
        return userMapper.userListToUserDtoList(users);
    }

    @Override
    public UserWithTasksDto getUserById(int id) {
        Optional<User> user = userRepo.findById(id);
        return userMapper.userToUserWithTasksDto(user.get());
    }

    @Override
    public UserWithTasksDto saveUser(UserWithTasksDto userWithTasksDto) {
        User user = userMapper.userWithTasksDtoToUser(userWithTasksDto);
        User addedUser = userRepo.save(user);
        return userMapper.userToUserWithTasksDto(addedUser);
    }

    @Override
    public void removeUser(UserWithTasksDto userWithTasksDto) {
        User user = userMapper.userWithTasksDtoToUser(userWithTasksDto);
        userRepo.delete(user);
    }
}
