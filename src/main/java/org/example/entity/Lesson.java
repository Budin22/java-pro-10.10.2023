package org.example.entity;

import java.time.LocalDateTime;

public class Lesson {
    private Integer id;
    private String name;
    private LocalDateTime updateAt;
    private Homework homework;

    public Lesson(Integer id, String name, Homework homework, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.homework = homework;
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", updateAt:" + updateAt +
                ", homework:" + homework +
                '}';
    }
}
