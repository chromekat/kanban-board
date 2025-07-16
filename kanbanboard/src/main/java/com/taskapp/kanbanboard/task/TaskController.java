package com.taskapp.kanbanboard.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1")
public class TaskController {

    // creating the service for tasks
    private TaskService taskService;


    // object mapper for patching
    private ObjectMapper objectMapper;

    // constructor
    @Autowired
    public TaskController(TaskService taskService, ObjectMapper objectMapper){
        this.taskService = taskService;
        this.objectMapper = objectMapper;
    }

    // endpoints
    @GetMapping("/tasks")
    public List<Task> findAll(){
        return taskService.findAll();
    }

    @GetMapping("/tasks/{taskId}")
    public Optional<Task> getTask(@PathVariable int taskId){

        // finding task by id
        Optional<Task> task = taskService.findById(taskId);

        // if the task isn't found throw exception
        if(task == null){
            throw new RuntimeException("Task with id " + taskId + " not found");
        }

        return task;
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task){

        task.setTaskId(0);

        return taskService.save(task);

    }

    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@RequestBody Task task){
        return taskService.save(task);
    }

    @PatchMapping("tasks/{taskId}")
    public Task patchTask(@PathVariable int taskId, @RequestBody Map<String, Object> patchPayload){

        Optional<Task> currentTaskRecord = taskService.findById(taskId);

        if(currentTaskRecord == null){
            throw new RuntimeException("Task with id " + taskId + " not found.");
        }

        // don't include id field for request body
        if(patchPayload.containsKey("task_id")){
            throw new RuntimeException("Cannot place task ID in request body");
        }

        // updating the task
        Task updatedTaskRecord = apply(patchPayload, currentTaskRecord);

        return taskService.save(updatedTaskRecord);

    }

    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable int taskId){
        taskService.deleteById(taskId);
    }

    // apply method for patching
    private Task apply(Map<String, Object> patchPayload, Optional<Task> currentTask){

        ObjectNode taskNode = objectMapper.convertValue(currentTask, ObjectNode.class);

        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        taskNode.setAll(patchNode);

        return objectMapper.convertValue(taskNode, Task.class);

    }

}
