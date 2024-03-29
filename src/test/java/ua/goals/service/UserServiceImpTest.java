package ua.goals.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.goals.config.TestConfig;
import ua.goals.model.dto.UserDto;
import ua.goals.model.dto.UserWithTasksDto;
import ua.goals.model.entity.User;
import ua.goals.model.mapper.UserMapper;
import ua.goals.repo.UserJpaRepo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
public class UserServiceImpTest {
    @Mock
    private UserJpaRepo userJpaRepo;
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
        userService = new UserServiceImp(userJpaRepo, userMapper);
        initTestUsers();
        testUserInDB = testUsers.get(0);
    }

    @Test
    public void getAllUserTest_success() {
        when(userJpaRepo.findAll()).thenReturn(testUsers);
        List<UserDto> userDtoList = userService.getAllUser();

        assertNotNull(userDtoList);
        assertNotEquals(userDtoList.size(), 0);
    }

    @Test
    public void saveTest_userSaved() {
        UserWithTasksDto userWithTasksDto = userMapper.userToUserWithTasksDto(testUserInDB);
        userWithTasksDto.setId(null);

        when(userJpaRepo.save(userMapper.userWithTasksDtoToUser(userWithTasksDto))).thenReturn(testUserInDB);
        UserWithTasksDto userSaved = userService.saveUser(userWithTasksDto);

        assertNotNull(userSaved);
        assertNotNull(userSaved.getId());
        assertEquals(userWithTasksDto.getName(), userSaved.getName());
    }

    @Test
    public void findTest_returnsNonNullUserById() {
        Integer testId = 14;
        when(userJpaRepo.findById(testId)).thenReturn(Optional.ofNullable(testUserInDB));
        UserWithTasksDto userWithTasksDto = userService.getUserById(testId);

        assertNotNull(userWithTasksDto);
        assertEquals(userWithTasksDto.getId(), testId);
    }

    @Test
    public void findTest_returnsNoSuchElementException() {
        Integer testId = 25;
        when(userJpaRepo.findById(testId)).thenThrow(new NoSuchElementException());
    }

    @Test
    public void removeTest_returnsNonNullForExistingUser() {
        UserWithTasksDto userWithTasksDto = userMapper.userToUserWithTasksDto(testUserInDB);
        userService.removeUser(userWithTasksDto);

        verify(userJpaRepo).delete(testUserInDB);
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
