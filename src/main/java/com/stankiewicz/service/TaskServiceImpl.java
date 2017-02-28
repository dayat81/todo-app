package com.stankiewicz.service;

import com.stankiewicz.domain.Task;
import com.stankiewicz.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Task findById(Long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public List<Task> findByDate(String date) {
        System.out.println("UWAGA data " + date);
        return findByDate(date);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public boolean isTaskExist(Task task) {
        return taskRepository.exists(task.getId());
    }



}
