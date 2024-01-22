package org.example.service;

import org.example.exception.LessonException;
import org.example.model.Homework;
import org.example.model.Lesson;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class LessonService {
    private final Connection connection;

    public LessonService(Connection connection) {
        this.connection = connection;
    }

    public Lesson addLesson(Lesson lessonDTO) {
        try {
            if (lessonDTO == null) throw new LessonException("lesson should be not null");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lesson (name, update_at) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lessonDTO.getName());
            preparedStatement.setString(2, lessonDTO.getUpdateAt().toString());
            int rowEffected = preparedStatement.executeUpdate();
            Lesson lesson = null;
            if (rowEffected > 0) {
                ResultSet genKey = preparedStatement.getGeneratedKeys();
                if (genKey.next()) {
                    int id = genKey.getInt(1);
                    lesson = new Lesson();
                    lesson.setId(id);
                    lesson.setName(lessonDTO.getName());
                    lesson.setUpdateAt(lessonDTO.getUpdateAt());

                }
            }
            return lesson;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in addLesson: " + e.getMessage());
        }
    }

    private Homework addHomework(Homework newHomework, Integer lessonId) {
        try {
            if (newHomework == null) throw new LessonException("Homework should be not null");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO homework (name, description, lesson_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newHomework.getName());
            preparedStatement.setString(2, newHomework.getDescription());
            preparedStatement.setInt(3, newHomework.getLessonId());
            int rowEffected = preparedStatement.executeUpdate();
            Homework homework = null;
            if (rowEffected > 0) {
                ResultSet genKey = preparedStatement.getGeneratedKeys();
                if (genKey.next()) {
                    int id = genKey.getInt(1);
                    homework = new Homework();
                    homework.setId(id);
                    homework.setName(newHomework.getName());
                    homework.setDescription(newHomework.getDescription());
                    homework.setLessonId(lessonId);
                }
            }
            return homework;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in addHomework: " + e.getMessage());
        }
    }

    public Lesson addLessonWithHomework(Lesson newLesson, Homework newHomework) {
        Lesson lesson = addLesson(newLesson);
        addHomework(newHomework, lesson.getId());
        return lesson;
    }

    public boolean deleteLessonById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM lesson AS l WHERE l.id = ?");
            preparedStatement.setInt(1, id);
            int rowEffected = preparedStatement.executeUpdate();
            return rowEffected > 0;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in deleteLessonById: " + e.getMessage());
        }
    }

    public List<Lesson> getAllLessons() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lesson");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Lesson> lessons = new LinkedList<>();

            while (resultSet.next()) {
                int lessonId = resultSet.getInt("id");
                String lessonName = resultSet.getString("name");
                LocalDateTime updateAt = (LocalDateTime) resultSet.getObject("update_at");
                Lesson lesson = new Lesson();
                lesson.setId(lessonId);
                lesson.setName(lessonName);
                lesson.setUpdateAt(updateAt);
                lessons.add(lesson);
            }
            return lessons;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in getAllLessons: " + e.getMessage());
        }
    }

    public List<Homework> getAllHomeworksByLessonId(Integer lessonId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM homework as h WHERE h.lesson_id = ?");
            preparedStatement.setInt(1, lessonId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Homework> homeworks = new LinkedList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Homework homework = new Homework();
                homework.setId(id);
                homework.setName(name);
                homework.setDescription(description);
                homework.setLessonId(lessonId);

                homeworks.add(homework);
            }
            return homeworks;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in getAllHomeworksByLessonId: " + e.getMessage());
        }
    }

    public Lesson getLessonById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lesson AS l JOIN homework AS h ON l.id = h.lesson_id WHERE l.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Lesson lesson = null;

            if (resultSet.next()) {

                String lessonName = resultSet.getString("name");
                LocalDateTime updateAt = (LocalDateTime) resultSet.getObject("update_at");

                lesson = new Lesson();
                lesson.setId(id);
                lesson.setName(lessonName);
                lesson.setUpdateAt(updateAt);
            }

            return lesson;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in getById: " + e.getMessage());
        }
    }
}
