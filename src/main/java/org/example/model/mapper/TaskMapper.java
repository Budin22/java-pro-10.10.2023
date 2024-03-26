package org.goals.model.mapper;

import org.goals.model.dto.TaskDto;
import org.goals.model.entity.Task;

import java.util.List;

public interface TaskMapper {
    List<TaskDto> taskListToTaskDtoList(List<Task> tasks);
    List<Task> taskDtoListToTaskList(List<TaskDto> tasks);
    TaskDto taskToTaskDto(Task task);
    Task taskDtoToTask(TaskDto taskDto);
}
