package org.example.db;

import org.example.exception.LessonException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private final Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
    }
    public void createTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE lesson (\n" +
                    "   id INT AUTO_INCREMENT NOT NULL,\n" +
                    "   name VARCHAR(256) NOT NULL,\n" +
                    "   update_at DATETIME NOT NULL,\n" +
                    "   PRIMARY KEY(id)\n" +
                    ")");
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = connection.prepareStatement("CREATE TABLE homework (\n" +
                    "   id INT AUTO_INCREMENT NOT NULL,\n" +
                    "   name VARCHAR(256) NOT NULL,\n" +
                    "   description VARCHAR(256) NOT NULL,\n" +
                    "   lesson_id INT NOT NULL,\n" +
                    "   PRIMARY KEY (id),\n" +
                    "   CONSTRAINT fk_lesson FOREIGN KEY (lesson_id) REFERENCES lesson(id) ON DELETE CASCADE\n" +
                    ")");
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in createTable: " + e.getMessage());
        }
    }

    public void addData() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lesson (name, update_at) VALUES\n" +
                    "    ('Algebra Basics', '2023-12-01 10:00:00'),\n" +
                    "    ('Literary Analysis', '2023-12-01 12:00:00'),\n" +
                    "    ('Space Exploration', '2023-12-01 16:00:00')");
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO homework (name, description, lesson_id) VALUES\n" +
                    "    ('Math Assignment', 'Solve problems from Chapter 5.', 1),\n" +
                    "    ('English Essay', 'Write an essay on your favorite book.', 2),\n" +
                    "    ('Science Project', 'Create a model of the solar system.', 3),\n" +
                    "    ('Math Assignment', 'Solve problems from Chapter 5.', 2),\n" +
                    "    ('English Essay', 'Write an essay on your favorite book.', 3),\n" +
                    "    ('Science Project', 'Create a model of the solar system.', 1),\n" +
                    "    ('Math Assignment', 'Solve problems from Chapter 5.', 3),\n" +
                    "    ('English Essay', 'Write an essay on your favorite book.', 1),\n" +
                    "    ('Science Project', 'Create a model of the solar system.', 2)");

            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            throw new LessonException("Got SQLException in createTable: " + e.getMessage());
        }
    }
}
