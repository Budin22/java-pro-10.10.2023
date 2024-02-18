package org.example.model.mapper;

import org.example.model.dto.TaskDto;
import org.example.model.entity.Task;
import org.glassfish.jersey.spi.Contract;

import java.util.List;

@Contract
public interface TaskMapper {
    List<TaskDto> taskListToTaskDtoList(List<Task> tasks);
    List<Task> taskDtoListToTaskList(List<TaskDto> tasks);
    TaskDto taskToTaskDto(Task task);
    Task taskDtoToTask(TaskDto taskDto);
}
