package org.goals.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Integer id;
    private String name;
    private String description;
    private String status;
    private LocalDateTime createdTime;
    private LocalDateTime deadline;
}
