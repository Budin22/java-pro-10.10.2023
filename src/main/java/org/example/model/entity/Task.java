package org.goals.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "t_task")
@Data
@Table(name = "t_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @Column(name = "task_status")
    private String status;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "deadline")
    private LocalDateTime deadline;
    @ManyToMany(mappedBy = "tasks")
    private List<User> users;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
