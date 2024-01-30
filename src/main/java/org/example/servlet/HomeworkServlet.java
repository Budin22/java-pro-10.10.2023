package org.example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Homework;
import org.example.model.Lesson;
import org.example.service.HomeworkService;
import org.example.service.LessonService;

import java.io.IOException;
import java.util.List;

public class HomeworkServlet extends HttpServlet {
    private final HomeworkService homeworkService;
    private final LessonService lessonService;

    public HomeworkServlet(HomeworkService homeworkService, LessonService lessonService) {
        this.homeworkService = homeworkService;
        this.lessonService = lessonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = Integer.parseInt(req.getPathInfo().substring(1));

        Lesson lesson = lessonService.getLessonById(id);
        List<Homework> homeworks = homeworkService.getAllHomeworksByLessonId(id);

        req.setAttribute("homeworks", homeworks);
        req.setAttribute("lesson", lesson);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/lesson.jsp");
        dispatcher.include(req, res);
    }
}
