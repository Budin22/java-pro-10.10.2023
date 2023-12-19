package org.example.entity;

public class Lesson {
    private Integer id;
    private String name;
    private Homework homework;

    public Lesson(Integer id, String name, Homework homework) {
        this.id = id;
        this.name = name;
        this.homework = homework;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", homework:" + homework +
                '}';
    }
}
