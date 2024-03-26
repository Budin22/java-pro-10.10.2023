package org.goals.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "t_category")
@Data
@Table(name = "t_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks;
}
