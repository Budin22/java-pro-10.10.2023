package org.example.di;

import org.example.model.entity.Order;
import org.example.model.entity.Product;
import org.glassfish.hk2.api.Factory;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactory implements Factory<Session> {
    @Override
    public Session provide() {
        Configuration conf = new Configuration();

        conf.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        conf.setProperty("hibernate.connection.url", System.getenv("MYSQL_URL"));
        conf.setProperty("hibernate.connection.username", System.getenv("MYSQL_USER"));
        conf.setProperty("hibernate.connection.password", System.getenv("MYSQL_PASSWORD"));
        conf.setProperty("hibernate.show_sql", System.getenv("MYSQL_SHOW_SQL"));

        conf.addAnnotatedClass(Order.class);
        conf.addAnnotatedClass(Product.class);

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        org.hibernate.SessionFactory sessionFactory = conf.buildSessionFactory(registry);
        return sessionFactory.openSession();
    }

    @Override
    public void dispose(Session instance) {

    }
}
