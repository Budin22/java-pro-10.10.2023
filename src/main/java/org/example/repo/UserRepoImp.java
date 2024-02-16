package org.example.repo;

import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.example.model.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@AllArgsConstructor
class UserRepoImp implements UserRepo {
    @Inject
    private Session session;

    @Override
    public List<User> getAllUser() {
        return session.createQuery("SELECT * FROM t_user", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return session.get(User.class, id);
    }

    @Override
    public User saveUser(User user) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(user);
            session.flush();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return user;
    }

    @Override
    public User removeUser(User user) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(user);
            session.flush();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return user;
    }
}
