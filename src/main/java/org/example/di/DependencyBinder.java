package org.example.di;

import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;
import org.example.model.mapper.*;
import org.example.repo.UserRepo;
import org.example.repo.UserRepoImp;
import org.example.service.UserService;
import org.example.service.UserServiceImp;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.Session;

@Provider
public class DependencyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(SessionFactory.class).to(Session.class).in(Singleton.class);
        bind(UserRepoImp.class).to(UserRepo.class).in(Singleton.class);
        bind(UserServiceImp.class).to(UserService.class).in(Singleton.class);
        bind(UserMapperImp.class).to(UserMapper.class).in(Singleton.class);
        bind(TaskMapperImp.class).to(TaskMapper.class).in(Singleton.class);
    }
}
