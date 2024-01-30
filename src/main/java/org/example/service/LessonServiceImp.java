package org.example.service;

import org.example.exception.LessonException;
import org.example.model.Lesson;
import org.example.util.MyLocalDateTime;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class LessonServiceImp implements LessonService {
    private final Connection connection;

    public LessonServiceImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Lesson addLesson(Lesson lesson) {
        try {
            if (lesson == null) throw new LessonException("lesson should be not null");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lesson (name, update_at) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, MyLocalDateTime.getLocalDateTimeInString());
            int rowEffected = preparedStatement.executeUpdate();
            Lesson addedLesson = null;
            if (rowEffected > 0) {
                ResultSet genKey = preparedStatement.getGeneratedKeys();
                if (genKey.next()) {
                    int id = genKey.getInt(1);
                    addedLesson = new Lesson();
                    addedLesson.setId(id);
                    addedLesson.setName(lesson.getName());
                    addedLesson.setUpdateAt(lesson.getUpdateAt());
                }
            }
            return addedLesson;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in addLesson: " + e.getMessage());
        }
    }

    @Override
    public List<Lesson> getAllLessons() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lesson");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Lesson> lessons = new LinkedList<>();

            while (resultSet.next()) {
                int lessonId = resultSet.getInt("id");
                String lessonName = resultSet.getString("name");
                LocalDateTime updateAt = MyLocalDateTime.getLocalDateTimeFromString(resultSet.getString("update_at"));
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

    @Override
    public Lesson getLessonById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lesson AS l WHERE l.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Lesson lesson = null;

            if (resultSet.next()) {

                String lessonName = resultSet.getString("name");
                LocalDateTime updateAt = MyLocalDateTime.getLocalDateTimeFromString(resultSet.getString("update_at"));

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
