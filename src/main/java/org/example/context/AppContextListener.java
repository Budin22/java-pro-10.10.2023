package org.example.context;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.servlet.UserServlet;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            UserServlet userServlet = new UserServlet();

            event.getServletContext()
                    .addServlet("UserServlet", userServlet)
                    .addMapping("/hello");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}
