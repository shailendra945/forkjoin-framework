package com.infoiv.async.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name = "sub_task_result")
public class SubTaskResultEntity implements Serializable {

    @Id
    private String subTaskResultId;
    private String subTaskId;
    private String taskId;
    private String processorType;
    private String result;
    private boolean success;
    private String errorMessage;
    private LocalDateTime processedAt;

    public SubTaskResultEntity() {
    }

    public SubTaskResultEntity(String subTaskResultId,String subTaskId, String taskId, String result, boolean success) {
        this.subTaskResultId = subTaskResultId;
        this.subTaskId = subTaskId;
        this.taskId = taskId;
        this.result = result;
        this.success = success;
        this.processedAt = LocalDateTime.now();
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

    public String getProcessorType() {
        return processorType;
    }

    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public String getSubTaskResultId() {
        return subTaskResultId;
    }

    public void setSubTaskResultId(String subTaskResultId) {
        this.subTaskResultId = subTaskResultId;
    }
}

