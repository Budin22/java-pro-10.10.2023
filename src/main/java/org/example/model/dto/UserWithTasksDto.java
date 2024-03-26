package org.goals.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithTasksDto {
    private Integer id;
    private String name;
    private List<TaskDto> tasks;
}
