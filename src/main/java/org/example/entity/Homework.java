package org.example.entity;

public class Homework {
    private Integer id;
    private String name;
    private String description;

    public Homework(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", description:'" + description + '\'' +
                '}';
    }
}
