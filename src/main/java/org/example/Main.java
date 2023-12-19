package org.example;

import org.example.dao.LessonDAO;
import org.example.entity.Lesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try(LessonDAO lessonDAO = new LessonDAO(DBConnection.getConnection())) {


            List<Lesson> lessons = lessonDAO.getLessons();


            Connection connection = DBConnection.getConnection();
            PreparedStatement statement1 = connection.prepareStatement("CREATE TABLE homework (\n" +
                    " \tid INT AUTO_INCREMENT NOT NULL,\n" +
                    " \t   name VARCHAR(256) NOT NULL,\n" +
                    " \t   description VARCHAR(256) NOT NULL,\n" +
                    "        PRIMARY KEY(id)\n" +
                    " );");

            PreparedStatement statement2 = connection.prepareStatement("INSERT INTO homework (name, description) VALUES\n" +
                    " ('Math Assignment', 'Solve problems from Chapter 5.'),\n" +
                    " ('English Essay', 'Write an essay on your favorite book.'),\n" +
                    " ('Science Project', 'Create a model of the solar system.');");

            statement1.execute();
            statement2.execute();

            PreparedStatement statement4 = connection.prepareStatement("CREATE TABLE lesson (\n" +
                    " \tid INT AUTO_INCREMENT NOT NULL,\n" +
                    "     name VARCHAR(256) NOT NULL,\n" +
                    "     homework_id int NOT NULL,\n" +
                    "     PRIMARY KEY(id),\n" +
                    "     CONSTRAINT FOREIGN KEY(homework_id) REFERENCES homework(id)\n" +
                    " );");
            statement4.execute();

            PreparedStatement statement3 = connection.prepareStatement("INSERT INTO lesson (name, homework_id) VALUES\n" +
                    " ('Algebra Basics', 1),\n" +
                    " ('Literary Analysis', 2),\n" +
                    " ('Space Exploration', 3);");

            statement3.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}