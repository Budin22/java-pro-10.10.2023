package org.example.repo;

import jakarta.inject.Inject;
import org.example.model.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@Service
public class UserRepoImp implements UserRepo {
    @Inject
    private Session session;

    @Override
    public List<User> getAllUser() {
        return session.createQuery("FROM t_user", User.class).list();
    }

    @Override
    public User getUserById(int id) {
        return session.find(User.class, id);
    }

    @Override
    public User saveUser(User user) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(user);
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
            session.clear();
            session.remove(user);
            session.flush();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return user;
    }
}
