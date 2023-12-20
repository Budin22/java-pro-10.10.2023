package org.example.dao;

import org.example.entity.Homework;
import org.example.entity.Lesson;
import org.example.entity.dto.HomeworkDTO;
import org.example.entity.dto.LessonDTO;
import org.example.exception.LessonException;

import java.io.Closeable;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class LessonMysqlDao implements Closeable, CrudDao<Lesson, LessonDTO> {
    private final Connection connection;

    public LessonMysqlDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Lesson add(LessonDTO lessonDTO) {
        try {
            if (lessonDTO == null) throw new LessonException("lessonDTO should be not null");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lesson (name, update_at) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lessonDTO.getName());
            preparedStatement.setString(2, lessonDTO.getUpdateAt().toString());
            int rowEffected = preparedStatement.executeUpdate();
            Lesson lesson = null;
            if (rowEffected > 0) {
                ResultSet genKey = preparedStatement.getGeneratedKeys();
                if (genKey.next()) {
                    int id = genKey.getInt(1);
                    lesson = new Lesson(id, lessonDTO.getName(), null, lessonDTO.getUpdateAt());
                }
            }
            return lesson;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in add without homework: " + e.getMessage());
        }
    }

    public Lesson add(LessonDTO lessonDTO, HomeworkDTO homeworkDTO) {
        try {
            if (lessonDTO == null) throw new LessonException("lessonDTO should be not null");
            if (homeworkDTO == null) throw new LessonException("homeworkDTO should be not null");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lesson (name, update_at) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lessonDTO.getName());
            preparedStatement.setString(2, lessonDTO.getUpdateAt().toString());
            int rowEffected = preparedStatement.executeUpdate();
            Lesson lesson = null;
            if (rowEffected > 0) {
                ResultSet genKey = preparedStatement.getGeneratedKeys();
                if (genKey.next()) {
                    int id = genKey.getInt(1);
                    homeworkDTO.setLessonId(id);
                    Homework homework = addHomework(homeworkDTO);
                    lesson = new Lesson(id, lessonDTO.getName(), homework, lessonDTO.getUpdateAt());
                }
            }
            return lesson;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in add with homework: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM lesson AS l WHERE l.id = ?");
            preparedStatement.setInt(1, id);
            int rowEffected = preparedStatement.executeUpdate();
            return rowEffected > 0;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in deleteById: " + e.getMessage());
        }
    }

    @Override
    public List<Lesson> getAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lesson AS l JOIN homework AS h on l.id = h.lesson_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Lesson> lessons = new LinkedList<>();

            while (resultSet.next()) {
                int homeworkIdId = resultSet.getInt("h.id");
                String homeworkName = resultSet.getString("h.name");
                String homeworkDescription = resultSet.getString("h.description");
                Homework homework = new Homework(homeworkIdId, homeworkName, homeworkDescription);

                int lessonId = resultSet.getInt("id");
                String lessonName = resultSet.getString("name");
                LocalDateTime updateAt = (LocalDateTime) resultSet.getObject("update_at");
                Lesson lesson = new Lesson(lessonId, lessonName, homework, updateAt);

                lessons.add(lesson);
            }
            return lessons;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in getAll: " + e.getMessage());
        }
    }

    @Override
    public Lesson getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lesson AS l JOIN homework AS h ON l.id = h.lesson_id WHERE l.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Lesson lesson = null;

            if (resultSet.next()) {

                int homeworkIdId = resultSet.getInt("h.id");
                String homeworkName = resultSet.getString("h.name");
                String homeworkDescription = resultSet.getString("h.description");
                Homework homework = new Homework(homeworkIdId, homeworkName, homeworkDescription);

                String name = resultSet.getString("name");
                LocalDateTime updateAt = (LocalDateTime) resultSet.getObject("update_at");
                lesson = new Lesson(id, name, homework, updateAt);
            }
            return lesson;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in getById: " + e.getMessage());
        }
    }

    private Homework addHomework(HomeworkDTO homeworkDTO) {
        try {
            if (homeworkDTO == null) throw new LessonException("homeworkDTO should be not null");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO homework (name, description, lesson_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, homeworkDTO.getName());
            preparedStatement.setString(2, homeworkDTO.getDescription());
            preparedStatement.setInt(3, homeworkDTO.getLessonId());
            int rowEffected = preparedStatement.executeUpdate();
            Homework homework = null;
            if (rowEffected > 0) {
                ResultSet genKey = preparedStatement.getGeneratedKeys();
                if (genKey.next()) {
                    int id = genKey.getInt(1);
                    homework = new Homework(id, homeworkDTO.getName(), homeworkDTO.getDescription());
                }
            }
            return homework;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in addHomework: " + e.getMessage());
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
