package org.example.dao;

import org.example.entity.Homework;
import org.example.entity.Lesson;
import org.example.entity.dto.LessonDTO;

import java.io.Closeable;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LessonDAO implements Closeable {
    private final Connection connection;

    public LessonDAO(Connection connection) {
        this.connection = connection;
    }

    public Lesson add(LessonDTO lessonDTO) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lesson (name, homework_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lessonDTO.getName());
            preparedStatement.setInt(2, lessonDTO.getHomeworkId());
            int rowEffected = preparedStatement.executeUpdate();
            Lesson lesson = null;
            if (rowEffected > 0) {
                ResultSet genKey = preparedStatement.getGeneratedKeys();
                int id = genKey.getInt(1);

                lesson = new Lesson();
                lesson.setId(id);
                lesson.setName(lessonDTO.getName());
                lesson.setHomework(getHomeworkById(lessonDTO.getHomeworkId()));
            }
            return lesson;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteLessonById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM lesson AS l WHERE l.id = ?");
            preparedStatement.setInt(1, id);
            int rowEffected = preparedStatement.executeUpdate();
            return rowEffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Lesson> getLessons() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lesson AS l JOIN homework AS h on l.homework_id = h.id");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Lesson> lessons = new LinkedList<>();

            if (resultSet.next()) {

                int homeworkIdId = resultSet.getInt("homework.id");
                String homeworkName = resultSet.getString("homework.name");
                String homeworkDescription = resultSet.getString("homework.description");
                Homework homework = new Homework(homeworkIdId, homeworkName, homeworkDescription);

                int lessonId = resultSet.getInt("id");
                String lessonName = resultSet.getString("name");
                Lesson lesson = new Lesson();
                lesson.setId(lessonId);
                lesson.setName(lessonName);
                lesson.setHomework(homework);

                lessons.add(lesson);
            }
            return lessons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Lesson getLessonById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lesson (name, homework_id) WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Lesson lesson = null;

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int homeworkId = resultSet.getInt("homework_id");
                lesson = new Lesson();
                lesson.setId(id);
                lesson.setName(name);
                lesson.setHomework(getHomeworkById(homeworkId));
            }
            return lesson;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Homework getHomeworkById(int id) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM homework WHERE id = ?");
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            Homework homework = null;

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                homework = new Homework(id, name, description);
            }
            return homework;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void close() {
        try {
            if (!connection.isClosed()) connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
