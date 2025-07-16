package com.taskapp.kanbanboard.column;

import jakarta.persistence.*;

@Entity
@Table(name = "task_columns")
public class ColumnCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_id")
    private int columnId;

    @Column(name = "column_category")
    private String category;

    public ColumnCategory(){

    }

    public ColumnCategory(String category) {
        this.category = category;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ColumnCategory{" +
                "columnId=" + columnId +
                ", category='" + category + '\'' +
                '}';
    }
}
