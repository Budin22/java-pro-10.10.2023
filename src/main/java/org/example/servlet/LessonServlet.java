package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Lesson;
import org.example.service.LessonService;

import java.io.IOException;
import java.util.List;

public class LessonServlet extends HttpServlet {
    private final LessonService lessonService;

    public LessonServlet(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/app.jsp").include(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String params = req.getReader().readLine();
        if (params == null) return;

        String[] paramsPar = params.split("=");
        if (paramsPar[0].equals("name")) {
            String name = paramsPar[1].replace("+", " ").replace("%2B", "+");
            Lesson lesson = new Lesson();
            lesson.setName(name);
            lessonService.addLesson(lesson);
            resp.sendRedirect(req.getContextPath() + "/lessons");
        } else {
            System.out.println("Some went wrong with add lesson");
        }
    }
}
