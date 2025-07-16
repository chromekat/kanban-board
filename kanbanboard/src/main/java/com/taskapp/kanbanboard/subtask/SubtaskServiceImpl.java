package com.taskapp.kanbanboard.subtask;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubtaskServiceImpl implements SubtaskService{

    private SubtaskRepository subtaskRepository;

    public SubtaskServiceImpl(SubtaskRepository subtaskRepository){
        this.subtaskRepository = subtaskRepository;
    }

    @Override
    public List<Subtask> findAll() {
        return subtaskRepository.findAll();
    }

    @Override
    public Optional<Subtask> findById(int id) {
        return subtaskRepository.findById(id);
    }

    @Override
    public Subtask save(Subtask subtask) {
        return subtaskRepository.save(subtask);
    }

    @Override
    public void deleteById(int id) {
        subtaskRepository.deleteById(id);
    }
}
