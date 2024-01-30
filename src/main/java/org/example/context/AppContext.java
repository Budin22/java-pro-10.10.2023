package org.example.context;

import org.example.service.*;
import org.example.servlet.HelloServlet;
import org.example.servlet.HomeworkServlet;
import org.example.servlet.LessonServlet;

import java.sql.Connection;

public class AppContext {
    private final LessonServlet lessonServlet;
    private final HomeworkServlet homeworkServlet;
    private final DBService dbService;
    private final HelloServlet helloServlet;

    public AppContext(Connection connection) {
        LessonService lessonService = new LessonServiceImp(connection);
        HomeworkService homeworkService = new HomeworkServiceImp(connection);

        this.lessonServlet = new LessonServlet(lessonService);
        this.homeworkServlet = new HomeworkServlet(homeworkService, lessonService);
        this.dbService = new DBServiceImp(connection);
        this.helloServlet = new HelloServlet();
    }

    public LessonServlet getLessonServlet() {
        return lessonServlet;
    }

    public HomeworkServlet getHomeworkServlet() {
        return homeworkServlet;
    }

    public DBService getDbService() {
        return dbService;
    }

    public HelloServlet getHelloServlet() {
        return helloServlet;
    }
}
