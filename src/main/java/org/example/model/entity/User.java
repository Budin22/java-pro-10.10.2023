package org.example.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "t_user")
@Data
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "t_user_task",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")})
    private List<Task> tasks;
}
