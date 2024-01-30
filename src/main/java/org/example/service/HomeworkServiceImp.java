package org.example.service;

import org.example.exception.LessonException;
import org.example.model.Homework;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class HomeworkServiceImp implements HomeworkService {
    private final Connection connection;

    public HomeworkServiceImp(Connection connection) {
        this.connection = connection;
    }

    @Override
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
}
