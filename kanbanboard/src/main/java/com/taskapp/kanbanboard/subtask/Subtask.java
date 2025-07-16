package com.taskapp.kanbanboard.subtask;

import jakarta.persistence.*;

@Entity
@Table(name = "subtasks")
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtask_id")
    private int subtaskId;

    @Column(name = "task_id")
    private int taskId;

    @Column(name = "description")
    private String subtaskDescription;

    @Column(name = "status")
    private String status;

    @Column(name = "completed")
    private boolean completed;

    public Subtask(){

    }

    public Subtask(int taskId, String subtaskDescription, String status, boolean completed) {
        this.taskId = taskId;
        this.subtaskDescription = subtaskDescription;
        this.status = status;
        this.completed = completed;
    }

    public int getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(int subtaskId) {
        this.subtaskId = subtaskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getSubtaskDescription() {
        return subtaskDescription;
    }

    public void setSubtaskDescription(String subtaskDescription) {
        this.subtaskDescription = subtaskDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "subtaskId=" + subtaskId +
                ", taskId=" + taskId +
                ", subtaskDescription='" + subtaskDescription + '\'' +
                ", status='" + status + '\'' +
                ", completed=" + completed +
                '}';
    }
}
