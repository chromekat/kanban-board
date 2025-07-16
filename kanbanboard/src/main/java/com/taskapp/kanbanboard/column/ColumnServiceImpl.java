package com.taskapp.kanbanboard.column;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColumnServiceImpl implements ColumnService{

    private ColumnRepository columnRepository;

    public ColumnServiceImpl(ColumnRepository columnRepository){
        this.columnRepository = columnRepository;
    }

    @Override
    public List<ColumnCategory> findAll() {
        return columnRepository.findAll();
    }

    @Override
    public Optional<ColumnCategory> findById(int id) {
       return columnRepository.findById(id);
    }

    @Override
    public ColumnCategory save(ColumnCategory columnCategory) {
        return columnRepository.save(columnCategory);
    }

    @Override
    public void deleteById(int id) {
        columnRepository.deleteById(id);
    }
}
