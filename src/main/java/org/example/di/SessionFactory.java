package org.example.di;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.entity.Category;
import org.example.model.entity.Task;
import org.example.model.entity.User;
import org.glassfish.hk2.api.Factory;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactory implements Factory<Session> {
    private static final Logger logger = LogManager.getLogger(SessionFactory.class);

    @Override
    public Session provide() {
        Configuration conf = new Configuration();

        conf.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        conf.setProperty("hibernate.connection.url", System.getenv("MYSQL_URL"));
        conf.setProperty("hibernate.connection.username", System.getenv("MYSQL_USER"));
        conf.setProperty("hibernate.connection.password", System.getenv("MYSQL_PASSWORD"));
        conf.setProperty("hibernate.show_sql", System.getenv("MYSQL_SHOW_SQL"));

        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Task.class);
        conf.addAnnotatedClass(User.class);

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        org.hibernate.SessionFactory sessionFactory = conf.buildSessionFactory(registry);
        logger.info("Session created");
        return sessionFactory.openSession();
    }

    @Override
    public void dispose(Session session) {

    }
}
