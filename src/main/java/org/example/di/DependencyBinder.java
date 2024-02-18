package org.example.di;

import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;
import org.example.model.mapper.*;
import org.example.repo.OrderRepo;
import org.example.repo.OrderRepoImp;
import org.example.repo.UserRepo;
import org.example.repo.UserRepoImp;
import org.example.service.OrderService;
import org.example.service.OrderServiceImp;
import org.example.service.UserService;
import org.example.service.UserServiceImp;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.Session;

import java.sql.Connection;

@Provider
public class DependencyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(ConnectionFactory.class).to(Connection.class).in(Singleton.class);
        bind(OrderRepoImp.class).to(OrderRepo.class).in(Singleton.class);
        bind(OrderServiceImp.class).to(OrderService.class).in(Singleton.class);
        bind(OrderMapperImp.class).to(OrderMapper.class).in(Singleton.class);

        bindFactory(SessionFactory.class).to(Session.class).in(Singleton.class);
        bind(UserRepoImp.class).to(UserRepo.class).in(Singleton.class);
        bind(UserServiceImp.class).to(UserService.class).in(Singleton.class);
        bind(UserMapperImp.class).to(UserMapper.class).in(Singleton.class);
        bind(TaskMapperImp.class).to(TaskMapper.class).in(Singleton.class);
    }
}
