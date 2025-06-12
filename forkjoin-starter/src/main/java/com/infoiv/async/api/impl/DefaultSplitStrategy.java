package com.infoiv.async.api.impl;

import com.infoiv.async.api.SplitStrategy;
import com.infoiv.async.config.ForkJoinProperties;
import com.infoiv.async.models.SubTask;
import com.infoiv.async.models.TaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component("defaultSplitStrategy")
public class DefaultSplitStrategy implements SplitStrategy {

    @Autowired
    private ForkJoinProperties forkJoinProperties;

    public List<SubTask> split(TaskRequest request, int subTaskCount) {

        String processStrategy = request.getProcessStrategy();
        if(processStrategy == null){
            return Collections.EMPTY_LIST;
        }
        String joinStrategy = request.getJoinStrategy();
        if(joinStrategy == null){
            joinStrategy = forkJoinProperties.getStrategies().getJoin();
        }
        List<SubTask> subTasks = new ArrayList<>();
        if(StringUtils.isEmpty(request.getTaskId())){
            request.setTaskId(UUID.randomUUID().toString());
        }
        for (int i = 0; i < subTaskCount; i++) {
            Map<String, Object> part = new HashMap<>(request.getPayload());
            part.put("index", i);
            subTasks.add(new SubTask(request.getTaskId(), UUID.randomUUID().toString(),joinStrategy,processStrategy,part));
        }
        return subTasks;
    }
}