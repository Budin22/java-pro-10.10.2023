package org.example.repo;

import org.example.model.entity.User;
import org.glassfish.jersey.spi.Contract;

import java.util.List;

@Contract
public interface UserRepo {
    List<User> getAllUser();

    User getUserById(int id);

    User saveUser(User user);

    User removeUser(User user);
}
