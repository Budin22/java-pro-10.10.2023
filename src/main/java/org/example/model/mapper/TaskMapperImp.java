package org.goals.model.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.goals.model.dto.TaskDto;
import org.goals.model.entity.Task;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TaskMapperImp implements TaskMapper {
    private static final Logger logger = LogManager.getLogger(TaskMapperImp.class);
    @Override
    public List<TaskDto> taskListToTaskDtoList(List<Task> tasks) {
        logger.debug("Method taskListToTaskDtoList received tasks: {}", tasks);
        List<TaskDto> taskDtoList = new LinkedList<>();
        if (tasks != null) {
            tasks.forEach(task -> {
                TaskDto taskDto = taskToTaskDto(task);
                taskDtoList.add(taskDto);
            });
        }
        logger.debug("Method taskListToTaskDtoList return taskDtoList: {}", taskDtoList);
        return taskDtoList;
    }

    @Override
    public List<Task> taskDtoListToTaskList(List<TaskDto> taskDtoList) {
        logger.debug("Method taskDtoListToTaskList received taskDtoList: {}", taskDtoList);
        List<Task> tasks = new LinkedList<>();
        if (taskDtoList != null) {
            taskDtoList.forEach(taskDto -> {
                Task task = taskDtoToTask(taskDto);
                tasks.add(task);
            });
        }
        logger.debug("Method taskDtoListToTaskList return tasks: {}", tasks);
        return tasks;
    }

    @Override
    public TaskDto taskToTaskDto(Task task) {
        logger.debug("Method taskToTaskDto received task: {}", task);
        TaskDto taskDto = null;
        if (task != null) {
            taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setName(task.getName());
            taskDto.setDescription(task.getDescription());
            taskDto.setStatus(task.getStatus());
            taskDto.setCreatedTime(task.getCreatedTime());
            taskDto.setDeadline(task.getDeadline());
        }
        logger.debug("Method taskToTaskDto return taskDto: {}", taskDto);
        return taskDto;
    }

    @Override
    public Task taskDtoToTask(TaskDto taskDto) {
        logger.debug("Method taskToTaskDto received taskDto: {}", taskDto);
        Task task = null;
        if (taskDto != null) {
            task = new Task();
            task.setId(taskDto.getId());
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setStatus(taskDto.getStatus());
            task.setCreatedTime(taskDto.getCreatedTime());
            task.setDeadline(taskDto.getDeadline());
        }
        logger.debug("Method taskToTaskDto return task: {}", task);
        return task;
    }
}
