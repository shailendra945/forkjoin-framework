package com.infoiv.async.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "join_results")
public class JoinResultEntity {
    @Id
    private String taskId;

    @Lob
    private String finalResult;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime joinedAt;

    public enum Status {
        COMPLETED, FAILED, IN_PROGRESS
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}