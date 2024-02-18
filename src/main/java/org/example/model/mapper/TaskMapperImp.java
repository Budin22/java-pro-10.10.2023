package org.example.model.mapper;

import org.example.model.dto.TaskDto;
import org.example.model.entity.Task;

import java.util.LinkedList;
import java.util.List;

public class TaskMapperImp implements TaskMapper {
    @Override
    public List<TaskDto> taskListToTaskDtoList(List<Task> tasks) {
        List<TaskDto> taskDtoList = new LinkedList<>();
        if (tasks != null) {
            tasks.forEach(task -> {
                TaskDto taskDto = taskToTaskDto(task);
                taskDtoList.add(taskDto);
            });
        }
        return taskDtoList;
    }

    @Override
    public List<Task> taskDtoListToTaskList(List<TaskDto> taskDtoList) {
        List<Task> tasks = new LinkedList<>();
        if (taskDtoList != null) {
            taskDtoList.forEach(taskDto -> {
                Task task = taskDtoToTask(taskDto);
                tasks.add(task);
            });
        }
        return tasks;
    }

    @Override
    public TaskDto taskToTaskDto(Task task) {
        if (task != null) {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setName(task.getName());
            taskDto.setDescription(task.getDescription());
            taskDto.setStatus(task.getStatus());
            taskDto.setCreatedTime(task.getCreatedTime());
            taskDto.setDeadline(task.getDeadline());
            return taskDto;
        }
        return null;
    }

    @Override
    public Task taskDtoToTask(TaskDto taskDto) {
        if (taskDto != null) {
            Task task = new Task();
            task.setId(taskDto.getId());
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setStatus(taskDto.getStatus());
            task.setCreatedTime(taskDto.getCreatedTime());
            task.setDeadline(taskDto.getDeadline());
            return task;
        }
        return null;
    }
}
