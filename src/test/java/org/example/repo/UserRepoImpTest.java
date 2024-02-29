package org.example.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.config.HibernateTestConfig;
import org.example.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRepoImpTest {
//    private Session testSession;
//    private SessionFactory testSessionFactory;
//    private UserRepo userRepo;

//    @BeforeEach
//    public void setUp() {
//        testSessionFactory = new HibernateTestConfig().getSessionFactory();
//        testSession = testSessionFactory.openSession();
//        initTestData();
//        userRepo = new UserRepoImp(testSession);
//    }
//
//    @Test
//    public void saveTest_userSaved() {
//        User user = new User();
//        user.setName("username");
//        user.setTasks(new LinkedList<>());
//
//        User userSaved = userRepo.saveUser(user);
//
//        assertNotNull(userSaved);
//        assertNotNull(userSaved.getId());
//        assertEquals(user.getName(), userSaved.getName());
//    }
//
//    @Test
//    public void findTest_returnsNonNullUserById() {
//        Integer testId = 1;
//        User user = userRepo.getUserById(testId);
//
//        assertNotNull(user);
//        assertEquals(user.getId(), testId);
//    }
//
//    @Test
//    public void findAllTest_returnsNonNullUserList() {
//        List<User> user = userRepo.getAllUser();
//        assertNotNull(user);
//    }
//
//    @Test
//    public void removeTest_returnsNonNullForExistingUser() {
//        User userInDB = userRepo.getUserById(2);
//        User removedUser = userRepo.removeUser(userInDB);
//        assertNotNull(removedUser);
//    }
//
//    @AfterEach
//    public void shotDown() {
//        testSession.close();
//        testSessionFactory.close();
//    }
//
//    private void initTestData() {
//        if (testSession != null) {
//            final EntityManager entityManager = testSessionFactory.createEntityManager();
//
//            try (final InputStream inputStream = getClass()
//                    .getClassLoader()
//                    .getResourceAsStream("data.sql");
//                 BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
//            ) {
//                entityManager.getTransaction().begin();
//                final Optional<String> scriptOpt = br.lines().reduce((acc, line) -> acc + line + "\n");
//
//                final Query nativeQuery = entityManager.createNativeQuery(scriptOpt.get());
//                nativeQuery.executeUpdate();
//                entityManager.getTransaction().commit();
//            } catch (IOException e) {
//                entityManager.getTransaction().rollback();
//            }
//        }
//    }
}
