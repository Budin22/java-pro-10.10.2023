package org.example;

import org.example.dao.LessonDao;
import org.example.dao.LessonDaoImp;
import org.example.db.DBConnectionHolder;
import org.example.db.Database;
import org.example.entity.Lesson;
import org.example.entity.dto.HomeworkDTO;
import org.example.entity.dto.LessonDTO;
import org.example.exception.LessonException;

import java.sql.Connection;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        try (Connection con = DBConnectionHolder.getInstance().getConnection()) {

            Database database = new Database(con);
            database.createTable();
            database.addData();

            LessonDao lessonDAO = new LessonDaoImp(con);
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setName("Swimming");
            lessonDTO.setUpdateAt(LocalDateTime.now());

            HomeworkDTO homeworkDTO = new HomeworkDTO();
            homeworkDTO.setName("swim");
            homeworkDTO.setDescription("Swim pool 3 times");

            System.out.println("Get all lessons with homeworks" + lessonDAO.getAll());
            System.out.println("Get lesson with id=1: " + lessonDAO.getById(1));
            System.out.println("Delete lesson with id=1: " + lessonDAO.deleteById(1));
            System.out.println("Get deleted lesson with id=1: " + lessonDAO.getById(1));
            Lesson addedLesson = lessonDAO.addLesson(lessonDTO);
            System.out.println("added lesson: " + addedLesson);
            homeworkDTO.setLessonId(addedLesson.getId());
            lessonDAO.addHomeworkToLesson(homeworkDTO);
            System.out.println("Get added lesson with homeworks: " + lessonDAO.getById(addedLesson.getId()));

        } catch (LessonException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}