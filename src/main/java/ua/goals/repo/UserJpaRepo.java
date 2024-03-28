package ua.goals.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goals.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<User, Integer> {
    List<User> findAll();
    Optional<User> findById(Integer id);
    User findUserByName(String name);
    User  save(User user);

    void delete(User user);
}
