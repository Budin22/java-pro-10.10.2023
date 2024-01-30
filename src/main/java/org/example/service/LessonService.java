package org.example.service;

import org.example.model.Lesson;

import java.util.List;

public interface LessonService {
    Lesson addLesson(Lesson lesson);

    List<Lesson> getAllLessons();

    Lesson getLessonById(int id);
}
