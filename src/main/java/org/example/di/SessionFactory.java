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
        String msqlDriver = System.getenv("MYSQL_DRIVER");
        String mysqlUrl = System.getenv("MYSQL_URL");
        String mysqlUser = System.getenv("MYSQL_USER");
        String mysqlPassword = System.getenv("MYSQL_PASSWORD");
        String mysqlShowSql = System.getenv("MYSQL_SHOW_SQL");

        conf.setProperty("hibernate.connection.driver_class", msqlDriver);
        conf.setProperty("hibernate.connection.url", mysqlUrl);
        conf.setProperty("hibernate.connection.username", mysqlUser);
        conf.setProperty("hibernate.connection.password", mysqlPassword);
        conf.setProperty("hibernate.show_sql", mysqlShowSql);

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
        session.close();
        logger.info("Session closed");
    }
}
