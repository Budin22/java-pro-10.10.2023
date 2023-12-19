package org.example;

import org.example.dao.LessonDAO;
import org.example.entity.Homework;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try(LessonDAO lessonDAO = new LessonDAO(DBConnection.getConnection())) {




//            PreparedStatement statement1 = connection.prepareStatement("CREATE TABLE homework (\n" +
//                    " \tid INT AUTO_INCREMENT NOT NULL,\n" +
//                    " \t   name VARCHAR(256) NOT NULL,\n" +
//                    " \t   description VARCHAR(256) NOT NULL,\n" +
//                    "        PRIMARY KEY(id)\n" +
//                    " );");
//
//            PreparedStatement statement2 = connection.prepareStatement("INSERT INTO homework (name, description) VALUES\n" +
//                    " ('Math Assignment', 'Solve problems from Chapter 5.'),\n" +
//                    " ('English Essay', 'Write an essay on your favorite book.'),\n" +
//                    " ('Science Project', 'Create a model of the solar system.');");
//
//            statement1.execute();
//            statement2.execute();

//            PreparedStatement statement = connection.prepareStatement("SELECT * from homework");
//
//            boolean data = statement.execute();
//            if (data) {
//                List<Homework> homeworkList = new LinkedList<>();
//                ResultSet resultSet = statement.getResultSet();
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String name = resultSet.getString("name");
//                    String description = resultSet.getString("description");
//                    Homework homework = new Homework(id, name, description);
//                    homeworkList.add(homework);
//                }
//                System.out.println(homeworkList);
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}