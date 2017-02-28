package com.stankiewicz.service;

import com.stankiewicz.domain.Task;

import java.util.List;


public interface TaskService {

    Task findById(Long id);

    List<Task> findByDate(String date);

    void saveTask(Task task);

    void deleteTask(Task task);

    void deleteTaskById(Long id);

    void deleteAllTasks();

    List<Task> findAllTasks();

    boolean isTaskExist(Task task);

}
