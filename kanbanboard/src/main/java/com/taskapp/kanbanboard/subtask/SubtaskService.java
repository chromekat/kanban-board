package com.taskapp.kanbanboard.subtask;

import java.util.List;
import java.util.Optional;


public interface SubtaskService {

    List<Subtask> findAll();

    Optional<Subtask> findById(int id);

    Subtask save(Subtask subtask);

    void deleteById(int id);

}
