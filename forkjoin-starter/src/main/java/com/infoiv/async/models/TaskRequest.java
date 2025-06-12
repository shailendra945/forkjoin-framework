package com.infoiv.async.models;

import java.io.Serializable;
import java.util.Map;

public class TaskRequest implements Serializable {

    private String taskId;
    private Map<String, Object> payload;
    private int    subTaskCount;
    private String splitStrategy;
    private String joinStrategy;
    private String processStrategy;
    private String status;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskRequest{" +
                "taskId='" + taskId + '\'' +
                ", payload=" + payload +
                ", subTaskCount=" + subTaskCount +
                ", splitStrategy='" + splitStrategy + '\'' +
                ", joinStrategy='" + joinStrategy + '\'' +
                ", processStrategy='" + processStrategy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
