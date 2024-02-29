package org.example.web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("org.example");

        DispatcherServlet dispatcher = new DispatcherServlet(context);
        ServletRegistration.Dynamic registry = servletContext.addServlet("dispatcher", dispatcher);
        registry.setLoadOnStartup(1);
        registry.addMapping("/*");
    }
}
