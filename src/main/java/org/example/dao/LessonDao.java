package org.example.dao;

import org.example.entity.Homework;
import org.example.entity.Lesson;
import org.example.entity.dto.HomeworkDTO;
import org.example.entity.dto.LessonDTO;

import java.util.List;

public interface LessonDao {
    Lesson addLesson(LessonDTO item);

    boolean deleteById(int id);

    List<Lesson> getAll();

    Lesson getById(int id);

    Homework addHomeworkToLesson(HomeworkDTO homeworkDTO);

    List<Homework> getAllHomeworkByLessonId(Integer id);
}
