package org.goals.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.goals.model.entity.Category;
import org.goals.model.entity.Task;
import org.goals.model.entity.User;
import org.goals.model.mapper.TaskMapperImp;
import org.goals.model.mapper.UserMapper;
import org.goals.model.mapper.UserMapperImp;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateTestConfig {
    @Bean
    public SessionFactory getSessionFactory(){
        Configuration conf = getConf();

        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Task.class);
        conf.addAnnotatedClass(User.class);

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        return conf.buildSessionFactory(registry);
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public UserMapper getUserMapper(){
        return new UserMapperImp(new TaskMapperImp());
    }

    private static Configuration getConf() {
        Configuration conf = new Configuration();

        conf.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        conf.setProperty("hibernate.connection.url", "jdbc:h2:mem:testdb");
        conf.setProperty("hibernate.connection.username", "root");
        conf.setProperty("hibernate.connection.password", "password");
        conf.setProperty("hibernate.show_sql", "true");
        conf.setProperty("hibernate.hbm2ddl.auto", "create");
        conf.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return conf;
    }
}
