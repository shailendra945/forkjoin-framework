package com.infoiv.async.models;

import java.io.Serializable;
import java.util.Map;

public class SubTask implements Serializable
{
    private String taskId;
    private String subTaskId;
    private Map<String, Object> data;
    private String joinStrategy;
    private String processStrategy;

    public SubTask(String taskId, String subTaskId,String joinStrategy,String processStrategy, Map<String, Object> chunkData) {
        this.taskId = taskId;
        this.subTaskId = subTaskId;
        this.data = chunkData;
        this.joinStrategy = joinStrategy;
        this.processStrategy = processStrategy;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "SubTask{" +
                "taskId='" + taskId + '\'' +
                ", subTaskId='" + subTaskId + '\'' +
                ", data=" + data +
                ", joinStrategy='" + joinStrategy + '\'' +
                ", processStrategy='" + processStrategy + '\'' +
                '}';
    }
}
