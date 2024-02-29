package org.example.config;

import org.example.model.entity.Category;
import org.example.model.entity.Task;
import org.example.model.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateTestConfig {
//    public SessionFactory getSessionFactory(){
//        Configuration conf = new Configuration();
//
//        conf.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
//        conf.setProperty("hibernate.connection.url", "jdbc:h2:mem:testdb");
//        conf.setProperty("hibernate.connection.username", "root");
//        conf.setProperty("hibernate.connection.password", "password");
//        conf.setProperty("hibernate.show_sql", "true");
//        conf.setProperty("hibernate.hbm2ddl.auto", "create");
//        conf.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//
//        conf.addAnnotatedClass(Category.class);
//        conf.addAnnotatedClass(Task.class);
//        conf.addAnnotatedClass(User.class);
//
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
//        return conf.buildSessionFactory(registry);
//    }
}
