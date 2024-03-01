package org.example.sevice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.HibernateTestConfig;
import org.example.model.dto.UserDto;
import org.example.model.dto.UserWithTasksDto;
import org.example.model.entity.User;
import org.example.model.mapper.TaskMapperImp;
import org.example.model.mapper.UserMapper;
import org.example.model.mapper.UserMapperImp;
import org.example.repo.UserRepo;
import org.example.service.UserService;
import org.example.service.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class UserServiceImpTest {
    @Mock
    private UserRepo userRepoMock;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;
    private UserService userService;
    private List<User> testUsers;
    private User testUserInDB;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        userMapper = new UserMapperImp(new TaskMapperImp());
        userService = new UserServiceImp(userRepoMock, userMapper);
        initTestUsers();
        testUserInDB = testUsers.get(0);
    }

    @Test
    public void getAllUserTest_success() {
        when(userRepoMock.getAllUser()).thenReturn(testUsers);
        List<UserDto> userDtoList = userService.getAllUser();

        assertNotNull(userDtoList);
        assertNotEquals(userDtoList.size(), 0);
    }

    @Test
    public void saveTest_userSaved() {

        UserWithTasksDto userWithTasksDto = userMapper.userToUserWithTasksDto(testUserInDB);
        userWithTasksDto.setId(null);

        when(userRepoMock.saveUser(userMapper.userWithTasksDtoToUser(userWithTasksDto))).thenReturn(testUserInDB);
        UserWithTasksDto userSaved = userService.saveUser(userWithTasksDto);

        assertNotNull(userSaved);
        assertNotNull(userSaved.getId());
        assertEquals(userWithTasksDto.getName(), userSaved.getName());
    }

    @Test
    public void findTest_returnsNonNullUserById() {
        Integer testId = 14;
        when(userRepoMock.getUserById(testId)).thenReturn(testUserInDB);
        UserWithTasksDto userWithTasksDto = userService.getUserById(testId);

        assertNotNull(userWithTasksDto);
        assertEquals(userWithTasksDto.getId(), testId);
    }

    @Test
    public void removeTest_returnsNonNullForExistingUser() {
        User userInDB = testUsers.get(0);
        when(userRepoMock.removeUser(userInDB)).thenReturn(userInDB);
        UserWithTasksDto removedUser = userService.removeUser(userMapper.userToUserWithTasksDto(userInDB));
        assertNotNull(removedUser);
    }

    private void initTestUsers() {
        try (final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("user-data.json");
        ) {
            testUsers = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
