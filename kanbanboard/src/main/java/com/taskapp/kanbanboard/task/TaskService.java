package com.taskapp.kanbanboard.task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll();

    Optional<Task> findById(int id);

    Task save(Task task);

    void deleteById(int id);

}
