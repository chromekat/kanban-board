package com.taskapp.kanbanboard.column;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1")
public class ColumnController {

    private ColumnService columnService;

    private ObjectMapper objectMapper;

    public ColumnController(ColumnService columnService, ObjectMapper objectMapper){
        this.columnService = columnService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/columns")
    public List<ColumnCategory> findAll(){
        return columnService.findAll();
    }

    @GetMapping("/columns/{columnId}")
    public Optional<ColumnCategory> findById(@PathVariable int columnId){

        Optional<ColumnCategory> column = columnService.findById(columnId);

        if(column == null){
            throw new RuntimeException("Column not found.");
        }

        return column;
    }

    @PostMapping("/columns")
    public ColumnCategory addColumn(@RequestBody ColumnCategory column){

        column.setColumnId(0);

        return columnService.save(column);

    }

    @PutMapping("/columns/{columnId}")
    public ColumnCategory updateColumn(@RequestBody ColumnCategory columnCategory){

        return columnService.save(columnCategory);

    }

    @PatchMapping("/columns/{columnId}")
    public ColumnCategory columnCategory (@PathVariable int columnId, @RequestBody Map<String, Object> patchPayload){
        Optional<ColumnCategory> currentColumn = columnService.findById(columnId);

        if(currentColumn == null){
            throw new RuntimeException("Subtask with id " + columnId + " not found.");
        }

        if(patchPayload.containsKey("subtask_id")){
            throw new RuntimeException("Subtask id not allowed in request body.");
        }

        ColumnCategory patchedColumn = apply(patchPayload, currentColumn);

        return columnService.save(patchedColumn);
    }

    @DeleteMapping("/columns/{columnId}")
    public void deleteColumn(@PathVariable int columnId){
        columnService.deleteById(columnId);

        System.out.println("Column with id " + columnId + " deleted.");
    }

    private ColumnCategory apply(Map<String, Object> patchPayload, Optional<ColumnCategory> currentColumn){
        ObjectNode columnNode = objectMapper.convertValue(currentColumn, ObjectNode.class);

        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        columnNode.setAll(patchNode);

        return objectMapper.convertValue(columnNode, ColumnCategory.class);
    }

}
