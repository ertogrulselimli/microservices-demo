package com.togrul.ms.accounts.controller;


import com.togrul.ms.accounts.dto.TaskDto;
import com.togrul.ms.accounts.entity.Task;
import com.togrul.ms.accounts.entity.TaskStatus;
import com.togrul.ms.accounts.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }


    @PostMapping(value = "/tasks")
    public ResponseEntity<String> postTask(@RequestBody TaskDto taskDto) {
        Task task = new Task(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        Task persistedTask = repository.save(task);
        return ResponseEntity.ok(persistedTask.getId().toString());
    }



    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity getTaskById(@PathVariable("id") final Long id) {
        Optional<Task> byId = repository.findById(id);
        if(byId.isPresent()) {
            return ResponseEntity.ok().body(byId.get().toDto());
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity updateTask(@RequestBody TaskDto taskDto) {
        Optional<Task> byId = repository.findById(Long.valueOf(taskDto.getId()));
        if(byId.isPresent()) {
            Task task = byId.get();
            Optional.ofNullable(taskDto.getDescription()).ifPresent(description->task.setDescription(description));
            Optional.ofNullable(taskDto.getTitle()).ifPresent(title->task.setTitle(title));
            if(taskDto.getStatus()!=null && !taskDto.getStatus().isEmpty()) {
                Optional<TaskStatus> taskStatus = Arrays.stream(TaskStatus.values()).filter(tsk -> taskDto.getStatus().equals(tsk)).findFirst();
                if(taskStatus.isPresent()) {
                    task.setTaskStatus(taskStatus.get());
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body("Available statuses are: CREATED,APPROVED,REJECTED,BLOCKED,DONE");
                }
            }
            repository.save(task);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<Void> deleteTaskByid(@PathVariable("id") Long taskId) {
        Optional<Task> byId = repository.findById(taskId);
        if(byId.isPresent()) {
            repository.delete(byId.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(value = "/tasks/describe/{id}")
    public ResponseEntity<String> descrineById(@PathVariable("id") final Long id) {
        Optional<Task> byId = repository.findById(id);
        if(byId.isPresent()) {
            TaskDto taskDto = byId.get().toDto();
            return ResponseEntity.ok().body("[Description of task ["+taskDto.getId()+": "+taskDto.getTitle()+"] is :"+taskDto.getDescription());
        } else {
            return ResponseEntity.ok().body("[Task with id ="+id+" does not exist]");
        }
    }


    @GetMapping(value = "/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> taskDtos = new ArrayList<>();
        repository.findAll().forEach(task -> {
            taskDtos.add(task.toDto());
        });
        return ResponseEntity.ok().body(taskDtos);
    }


}
