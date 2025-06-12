package com.infoiv.async.entities;

import com.infoiv.async.converter.JsonMapConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name="task")
public class TaskEntity {

    @Id
    private String taskId;

    private String type;
    private String status; // NEW, IN_PROGRESS, COMPLETED, FAILED

    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> payload;

    private int subTaskCount;

    private String splitStrategy;
    private String joinStrategy;
    private String processStrategy;

    // Getters and Setters

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public int getSubTaskCount() {
        return subTaskCount;
    }

    public void setSubTaskCount(int subTaskCount) {
        this.subTaskCount = subTaskCount;
    }

    public String getSplitStrategy() {
        return splitStrategy;
    }

    public void setSplitStrategy(String splitStrategy) {
        this.splitStrategy = splitStrategy;
    }

    public String getJoinStrategy() {
        return joinStrategy;
    }

    public void setJoinStrategy(String joinStrategy) {
        this.joinStrategy = joinStrategy;
    }

    public String getProcessStrategy() {
        return processStrategy;
    }

    public void setProcessStrategy(String processStrategy) {
        this.processStrategy = processStrategy;
    }
}
