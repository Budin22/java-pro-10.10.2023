package org.example;

import jakarta.ws.rs.ApplicationPath;
import org.example.di.DependencyBinder;
import org.example.resource.OrderResource;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class App extends ResourceConfig {
    public App() {
        register(OrderResource.class);
        register(new DependencyBinder());
        packages("org.example");
    }
}
