package org.example.repo;

import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@Service
public class UserRepoImp implements UserRepo {
    @Inject
    private Session session;
    private static final Logger logger = LogManager.getLogger(UserRepoImp.class);

    @Override
    public List<User> getAllUser() {
        return session.createQuery("FROM t_user", User.class).list();
    }

    @Override
    public User getUserById(int id) {
        logger.debug("Call getUserById with id: {}", id);
        return session.find(User.class, id);
    }

    @Override
    public User saveUser(User user) {
        Transaction transaction = null;
        logger.debug("Call saveUser with user: {}", user);
        try {
            transaction = session.beginTransaction();
            session.merge(user);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error in saveUser e: {}", e);
        }
        logger.debug("Return saved user: {}", user);
        return user;
    }

    @Override
    public User removeUser(User user) {
        Transaction transaction = null;
        logger.debug("Call removeUser with user: {}", user);
        try {
            transaction = session.beginTransaction();
            session.clear();
            session.remove(user);
            session.flush();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error in removeUser e: {}", e);
        }
        logger.debug("Return removed user: {}", user);
        return user;
    }
}
