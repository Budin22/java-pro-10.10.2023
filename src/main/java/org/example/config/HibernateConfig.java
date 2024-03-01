package org.example.config;

import org.example.model.entity.Category;
import org.example.model.entity.Task;
import org.example.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:hibernate.properties")
public class HibernateConfig {
    @Value("${db.driver}")
    private String driverName;
    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.user}")
    private String dbUser;
    @Value("${db.password}")
    private String dbPassword;
    @Value("${db.show-sql}")
    private String showSlq;

    @Bean
    public Session session() {
        Configuration config = new Configuration();
        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", driverName);
        props.put("hibernate.connection.url", dbUrl);
        props.put("hibernate.connection.username", dbUser);
        props.put("hibernate.connection.password", dbPassword);
        props.put("hibernate.show_sql", showSlq);

        config.setProperties(props);
        config.addAnnotatedClass(Category.class);
        config.addAnnotatedClass(Task.class);
        config.addAnnotatedClass(User.class);

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .build();

        SessionFactory sessionFactory = config.buildSessionFactory(registry);
        return sessionFactory.openSession();
    }
}
