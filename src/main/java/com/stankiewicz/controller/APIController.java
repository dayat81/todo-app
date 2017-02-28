package com.stankiewicz.controller;

import com.stankiewicz.domain.Task;
import com.stankiewicz.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Piotrek on 21.02.2017.
 */
@RestController
@RequestMapping("/api")
public class APIController {

    public static final Logger logger = LoggerFactory.getLogger(APIController.class);

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/task/", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.findAllTasks();
        if(tasks.isEmpty())
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/task/", method = RequestMethod.POST)
    public ResponseEntity<?> createTask(@RequestBody Task task, UriComponentsBuilder uriBuilder){
        //check if task exist
       // List<Task> t = taskService.findByDate(task.getDate());
        //if (t != null)
        //    return new ResponseEntity<ErrorTemplate>(new ErrorTemplate("You have task created on that date"), HttpStatus.CONFLICT);
        taskService.saveTask(task);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/api/task/{id}").buildAndExpand(task.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/task/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllTasks() {
        taskService.deleteAllTasks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id){
        Task user = taskService.findById(id);
        if(user == null)
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Task>(user,HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> editTask(@PathVariable("id") Long id, @RequestBody Task task){
        Task t = taskService.findById(id);
        if (t == null)
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        t.setDescription(task.getDescription());
        t.setDate(task.getDate());
        taskService.saveTask(t);
        return new ResponseEntity<Task>(t, HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id){
        Task t = taskService.findById(id);
        if(t == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
