package org.example.sevice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.example.model.mapper.UserMapper;
import org.example.model.mapper.UserMapperImp;
import org.example.repo.UserRepo;
import org.example.service.UserService;
import org.example.service.UserServiceImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceImpTest {
    @Mock
    private UserRepo userRepoMock;
    private UserMapper userMapper;
    private UserService userService;
    private ObjectMapper objectMapper;
    private List<User> testUsers;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        userMapper = new UserMapperImp();
        userService = new UserServiceImp(userRepoMock, userMapper);
        initTestUsers();
    }

    @Test
    public void getAllUserTest_success(){
        when(userRepoMock.getAllUser()).thenReturn(testUsers);
        List<UserDto> userDtoList = userService.getAllUser();

        assertNotNull(userDtoList);
        assertNotEquals(userDtoList.size(), 0);
    }

    @AfterEach
    public void shutdown(){

    }

    private void initTestUsers() {
        try(final InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("user-data.json");
        ) {
            testUsers = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
