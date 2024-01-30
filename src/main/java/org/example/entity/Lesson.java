package org.example.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Lesson {
    private Integer id;
    private String name;
    private LocalDateTime updateAt;
    private List<Homework> homework;

    public Lesson(Integer id, String name, List<Homework> homework, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.homework = homework;
        this.updateAt = updateAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public List<Homework> getHomework() {
        return homework;
    }

    public void setHomework(List<Homework> homework) {
        this.homework = homework;
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
