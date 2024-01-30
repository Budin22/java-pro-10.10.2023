package org.example.context;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.dbmanager.DBConnectionHolder;
import org.example.service.DBService;
import org.example.servlet.HelloServlet;
import org.example.servlet.HomeworkServlet;
import org.example.servlet.LessonServlet;

import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ServletContext context = event.getServletContext();
            Connection connection = DBConnectionHolder.getInstance().getConnection();

            AppContext appContext = new AppContext(connection);

            LessonServlet lessonServlet = appContext.getLessonServlet();
            HomeworkServlet homeworkServlet = appContext.getHomeworkServlet();
            DBService dbService = appContext.getDbService();
            HelloServlet helloServlet = appContext.getHelloServlet();

            dbService.createTable();
            dbService.addData();

            context.setAttribute("connection", connection);

            context.addServlet("LessonServlet", lessonServlet)
                    .addMapping("/lessons");

            context.addServlet("HomeworkServlet", homeworkServlet)
                    .addMapping("/lesson/*");

            context.addServlet("HelloServlet", helloServlet)
                    .addMapping("/hello");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            Connection connection = (Connection) event.getServletContext().getAttribute("connection");
            if (!connection.isClosed()) connection.close();
        } catch (SQLException e) {
            System.out.println("problems with disconnection");
        }

    }
}
