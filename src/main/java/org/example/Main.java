package org.example;

import org.example.dao.LessonMysqlDao;
import org.example.db.DBConnection;
import org.example.entity.dto.HomeworkDTO;
import org.example.entity.dto.LessonDTO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(LessonMysqlDao lessonMysqlDAO = new LessonMysqlDao(DBConnection.getConnection())) {
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setName("Swimming");

            HomeworkDTO homeworkDTO = new HomeworkDTO();
            homeworkDTO.setName("swim");
            homeworkDTO.setDescription("Swim pool 3 times");

            System.out.println("Get all lessons with homeworks" + lessonMysqlDAO.getAll());
            System.out.println("Get lesson with id=1: " + lessonMysqlDAO.getById(1));
            System.out.println("Delete lesson with id=1: " + lessonMysqlDAO.deleteById(1));
            System.out.println("Get deleted lesson with id=1: " + lessonMysqlDAO.getById(1));
            System.out.println("lesson with homework: " + lessonMysqlDAO.add(lessonDTO, homeworkDTO));
            System.out.println("lesson without homework: " + lessonMysqlDAO.add(lessonDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}