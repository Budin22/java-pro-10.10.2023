package org.example.dao;

import org.example.entity.Homework;
import org.example.entity.Lesson;
import org.example.entity.dto.LessonDTO;

import java.io.Closeable;
import java.sql.*;

public class LessonDAO implements Closeable {
    private final Connection connection;

    public LessonDAO(Connection connection) {
        this.connection = connection;
    }

    public Lesson add(LessonDTO lessonDTO){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lesson (name, homework_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lessonDTO.getName());
            preparedStatement.setInt(2, lessonDTO.getHomeworkId());
            int rowEffected = preparedStatement.executeUpdate();
            Lesson lesson = null;
            if(rowEffected > 0){
                ResultSet genKey = preparedStatement.getGeneratedKeys();
                int id = genKey.getInt(1);

                PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM homework WHERE id = ?");
                preparedStatement1.setInt(1, lessonDTO.getHomeworkId());
                ResultSet resultSet = preparedStatement1.executeQuery();
                Homework homework = null;
                if(resultSet.next()){
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    homework = new Homework(lessonDTO.getHomeworkId(), name, description);
                }

                lesson = new Lesson();
                lesson.setId(id);
                lesson.setName(lessonDTO.getName());
                lesson.setHomework(homework);
            }
            return lesson;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try{
            if(!connection.isClosed()) connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
