package com.infoiv.async.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SubTaskResult implements Serializable {
    private String subTaskResultId;
    private String subTaskId;
    private String taskId;
    private String processorType;
    private String result; // JSON string or plain result string
    private boolean success;
    private String errorMessage;
    private LocalDateTime processedAt;
    private String joinStrategy;
    public SubTaskResult() {
    }

    public SubTaskResult(String subTaskResultId, String subTaskId, String taskId, String processorType, String result, boolean success, String errorMessage, LocalDateTime processedAt, String joinStrategy) {
        this.subTaskResultId = subTaskResultId;
        this.subTaskId = subTaskId;
        this.taskId = taskId;
        this.processorType = processorType;
        this.result = result;
        this.success = success;
        this.errorMessage = errorMessage;
        this.processedAt = processedAt;
        this.joinStrategy = joinStrategy;
    }

    public String getSubTaskResultId() {
        return subTaskResultId;
    }

    public void setSubTaskResultId(String subTaskResultId) {
        this.subTaskResultId = subTaskResultId;
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

    public String getJoinStrategy() {
        return joinStrategy;
    }

    public void setJoinStrategy(String joinStrategy) {
        this.joinStrategy = joinStrategy;
    }

    @Override
    public String toString() {
        return "SubTaskResult{" +
                "subTaskResultId='" + subTaskResultId + '\'' +
                ", subTaskId='" + subTaskId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", processorType='" + processorType + '\'' +
                ", result='" + result + '\'' +
                ", success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                ", processedAt=" + processedAt +
                ", joinStrategy='" + joinStrategy + '\'' +
                '}';
    }
}
