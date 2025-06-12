package com.infoiv.async.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sub_tasks")
public class SubTaskEntity {
    @Id
    private String subTaskId;

    private String taskId;
    private String queueName;

    @Lob
    private String payload;

    @Enumerated(EnumType.STRING)
    private Status status;

    private int attemptCount;

    @Lob
    private String result;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String processStrategy;
    private String joinStrategy;

    public enum Status {
        PENDING, PROCESSING, SUCCESS, FAILED, RETRY
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProcessStrategy() {
        return processStrategy;
    }

    public void setProcessStrategy(String processStrategy) {
        this.processStrategy = processStrategy;
    }

    public String getJoinStrategy() {
        return joinStrategy;
    }

    public void setJoinStrategy(String joinStrategy) {
        this.joinStrategy = joinStrategy;
    }
}
