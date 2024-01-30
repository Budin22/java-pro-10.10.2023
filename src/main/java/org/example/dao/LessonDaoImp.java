package org.example.dao;

import org.example.entity.Homework;
import org.example.entity.Lesson;
import org.example.entity.dto.HomeworkDTO;
import org.example.entity.dto.LessonDTO;
import org.example.exception.LessonException;
import org.example.util.MyLocalDateTime;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class LessonDaoImp implements LessonDao {
    private final Connection connection;

    public LessonDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Lesson addLesson(LessonDTO lessonDTO) {
        try {
            if (lessonDTO == null) throw new LessonException("lessonDTO should be not null");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lesson (name, update_at) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lessonDTO.getName());
            preparedStatement.setString(2, MyLocalDateTime.getLocalDateTimeInString());
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lesson");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Lesson> lessons = new LinkedList<>();

            while (resultSet.next()) {
                int lessonId = resultSet.getInt("id");
                String lessonName = resultSet.getString("name");
                LocalDateTime updateAt = MyLocalDateTime.getLocalDateTimeFromString(resultSet.getString("update_at"));
                Lesson lesson = new Lesson(lessonId, lessonName, null, updateAt);

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
                String name = resultSet.getString("name");
                LocalDateTime updateAt = MyLocalDateTime.getLocalDateTimeFromString(resultSet.getString("update_at"));
                lesson = new Lesson(id, name, getAllHomeworkByLessonId(id), updateAt);
            }
            return lesson;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in getById: " + e.getMessage());
        }
    }

    @Override
    public List<Homework> getAllHomeworkByLessonId(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM homework WHERE lesson_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Homework> homeworks = new LinkedList<>();

            while (resultSet.next()) {
                String name = resultSet.getString("homework.name");
                String description = resultSet.getString("homework.description");
                Homework homework = new Homework(id, name, description);
                homeworks.add(homework);
            }
            return homeworks;
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in getAllHomeworkByLessonId: " + e.getMessage());
        }
    }

    @Override
    public Homework addHomeworkToLesson(HomeworkDTO homeworkDTO) {
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
}
