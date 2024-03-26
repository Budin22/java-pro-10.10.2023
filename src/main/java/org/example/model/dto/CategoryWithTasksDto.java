package org.goals.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.goals.model.entity.Task;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWithTasksDto {
    private Integer id;
    private String name;
    private String description;
    private List<Task> tasks;
}
