package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Application {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.configure("hibernate.annotation.cfg.xml");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        try (SessionFactory sessionFactory = conf.buildSessionFactory(registry)) {
            Session session = sessionFactory.openSession();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
