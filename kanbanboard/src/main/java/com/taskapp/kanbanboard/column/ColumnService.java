package com.taskapp.kanbanboard.column;

import java.util.List;
import java.util.Optional;

public interface ColumnService {
    List<ColumnCategory> findAll();

    Optional<ColumnCategory> findById(int id);

    ColumnCategory save(ColumnCategory columnCategory);

    void deleteById(int id);

}
