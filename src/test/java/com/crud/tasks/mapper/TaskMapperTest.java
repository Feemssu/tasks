package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(task.getId(), taskDto.getId());
    }

    @Test
    void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "test", "test");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(taskDto.getTitle(), task.getTitle());
    }

    @Test
    void testMapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "test", "test");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(taskDtos.get(0).getContent(), task.getContent());
    }
}
