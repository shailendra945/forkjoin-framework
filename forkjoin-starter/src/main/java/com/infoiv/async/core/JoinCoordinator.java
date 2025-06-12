package com.infoiv.async.core;

import com.infoiv.async.models.SubTaskResult;
import com.infoiv.async.svervice.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JoinCoordinator {

    @Autowired
    private SubTaskService subTaskService;

    // todo: need to clean this map once task completed
    private final Map<String, List<SubTaskResult>> resultStore = new ConcurrentHashMap<>();

    public void collectResult(SubTaskResult result) {
        resultStore.computeIfAbsent(result.getTaskId(), k -> new ArrayList<>()).add(result);
    }

    public boolean isReadyToJoin(String taskId) {
        long subTaskCount = subTaskService.getSubTaskCountByTaskId(taskId);
        return getResults(taskId).size() == subTaskCount ? true : false;
    }

    public List<SubTaskResult> getResults(String taskId) {
         return resultStore.get(taskId);
    }

    public List<SubTaskResult> deleteResults(String taskId){
        return resultStore.remove(taskId);
    }


}
