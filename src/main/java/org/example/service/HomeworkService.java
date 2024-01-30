package org.example.service;

import org.example.model.Homework;

import java.util.List;

public interface HomeworkService {
    List<Homework> getAllHomeworksByLessonId(Integer lessonId);
}
