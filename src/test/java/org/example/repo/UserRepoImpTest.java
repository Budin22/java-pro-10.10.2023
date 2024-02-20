package org.example.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.entity.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRepoImpTest {
    private Session session;
    private UserRepo userRepo;
    private static final Logger logger = LogManager.getLogger(UserRepoImpTest.class);


    @Test
    public void saveTest_returnsNonNullUser(){
        User user = new User();
        user.setName("username");
        user.setTasks(new LinkedList<>());

        User savedUser = userRepo.saveUser(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals(savedUser.getName(), user.getName());
    }
}
