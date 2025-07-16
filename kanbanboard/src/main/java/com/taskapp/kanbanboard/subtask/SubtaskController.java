package com.taskapp.kanbanboard.subtask;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1")
public class SubtaskController {

    private SubtaskService subtaskService;
    private ObjectMapper objectMapper;

    public SubtaskController(SubtaskService subtaskService, ObjectMapper objectMapper){
        this.subtaskService = subtaskService;
        this.objectMapper = objectMapper;
    }

    // endpoints and mappings
    @GetMapping("/subtasks")
    public List<Subtask> findAll(){
        return subtaskService.findAll();
    }

    @GetMapping("/subtasks/{subtaskId}")
    public Optional<Subtask> findById(@PathVariable int subtaskId){

        Optional<Subtask> subtask = subtaskService.findById(subtaskId);

        if(subtask == null){
            throw new RuntimeException("Subtask with id " + subtaskId + " not found.");
        } 

        return subtask;
    }

    @PostMapping("/subtasks")
    public Subtask addSubtask(@RequestBody Subtask subtask){

        subtask.setSubtaskId(0);

        return subtaskService.save(subtask);
    }

    @PutMapping("/subtasks{subtaskId}")
    public Subtask updateSubtask(@RequestBody Subtask subtask){
        return subtaskService.save(subtask);
    }

    @PatchMapping("/subtasks/{subtaskId}")
    public Subtask patchSubtask(@PathVariable int subtaskId, @RequestBody Map<String, Object> patchPayload){

        Optional<Subtask> currentSubtask = subtaskService.findById(subtaskId);

        if(currentSubtask == null){
            throw new RuntimeException("Subtask with id " + subtaskId + " not found.");
        }

        if(patchPayload.containsKey("subtask_id")){
            throw new RuntimeException("Subtask id not allowed in request body.");
        }

        Subtask patchedSubtask = apply(patchPayload, currentSubtask);

        return subtaskService.save(patchedSubtask);

    }

    @DeleteMapping("/subtasks/{subtaskId}")
    public void deleteById(@PathVariable int subtaskId){

        Optional<Subtask> subtask = subtaskService.findById(subtaskId);

        if(subtask == null){
            throw new RuntimeException("Subtask with id " + subtaskId + " not found.");
        }

        subtaskService.deleteById(subtaskId);

        System.out.println("Deleted employee with id " + subtaskId);

    }


    private Subtask apply(Map<String, Object> patchPayload, Optional<Subtask> currentSubtask){
        ObjectNode subtaskNode = objectMapper.convertValue(currentSubtask, ObjectNode.class);

        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        subtaskNode.setAll(patchNode);

        return objectMapper.convertValue(subtaskNode, Subtask.class);
    }

}
