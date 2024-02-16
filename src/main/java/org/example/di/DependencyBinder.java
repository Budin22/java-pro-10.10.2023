package org.example.di;

import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;
import org.example.model.mapper.OrderMapper;
import org.example.model.mapper.OrderMapperImp;
import org.example.repo.OrderRepo;
import org.example.repo.OrderRepoImp;
import org.example.service.OrderService;
import org.example.service.OrderServiceImp;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.Session;

import java.sql.Connection;

@Provider
public class DependencyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bindFactory(ConnectionFactory.class).to(Connection.class).in(Singleton.class);
        bindFactory(SessionFactory.class).to(Session.class).in(Singleton.class);
        bind(OrderRepoImp.class).to(OrderRepo.class).in(Singleton.class);
        bind(OrderServiceImp.class).to(OrderService.class).in(Singleton.class);
        bind(OrderMapperImp.class).to(OrderMapper.class).in(Singleton.class);
    }
}
