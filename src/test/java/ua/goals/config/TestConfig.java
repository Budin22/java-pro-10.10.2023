package ua.goals.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.goals.model.mapper.UserMapper;
import ua.goals.model.mapper.UserMapperImpl;

@Configuration
public class TestConfig {
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public UserMapper getUserMapper() {
        return new UserMapperImpl();
    }
}
