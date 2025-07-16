package com.taskapp.kanbanboard.column;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<ColumnCategory, Integer> {

}
