package com.taskapp.kanbanboard.task;


import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "title")
    private String taskTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "column_category")
    private int columnCategory;

    @Column(name = "priority")
    private String priority;

    public Task(){

    }

    public Task(String taskTitle, String description, int columnCategory, String priority) {
        this.taskTitle = taskTitle;
        this.description = description;
        this.columnCategory = columnCategory;
        this.priority = priority;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColumnCategory() {
        return columnCategory;
    }

    public void setColumnCategory(int columnCategory) {
        this.columnCategory = columnCategory;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskTitle='" + taskTitle + '\'' +
                ", description='" + description + '\'' +
                ", columnCategory=" + columnCategory +
                ", priority='" + priority + '\'' +
                '}';
    }
}
