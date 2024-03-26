package org.goals.repo;

import org.goals.model.entity.User;

import java.util.List;

public interface UserRepo {
    List<User> getAllUser();

    User getUserById(int id);

    User saveUser(User user);

    User removeUser(User user);
}
