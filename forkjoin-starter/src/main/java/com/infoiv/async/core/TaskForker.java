package com.infoiv.async.core;

import com.infoiv.async.api.SplitStrategy;
import com.infoiv.async.config.ForkJoinProperties;
import com.infoiv.async.controller.TaskController;
import com.infoiv.async.enums.TaskStatus;
import com.infoiv.async.models.SubTask;
import com.infoiv.async.models.TaskRequest;

import com.infoiv.async.svervice.TaskService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskForker{

    public static final Logger logger = LoggerFactory.getLogger(TaskForker.class);

    private final TaskDispatcher dispatcher;
    @Autowired
    private ForkJoinProperties forkJoinProperties;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TaskService taskService;

    public TaskForker(TaskDispatcher dispatcher) {
       this.dispatcher = dispatcher;
    }

    @Transactional
    public void forkAndDispatch(TaskRequest request) {
        int count = request.getSubTaskCount();
        if(count <= 0){
            count = forkJoinProperties.getSubtaskQueueCount();
        }
        SplitStrategy splitStrategy = (SplitStrategy)
                applicationContext.getBean(request.getSplitStrategy());
        if(splitStrategy == null){
            splitStrategy = (SplitStrategy)
                    applicationContext.getBean(forkJoinProperties.getStrategies().getSplit());
        }
        if(splitStrategy == null){
            logger.error("***** forkAndDispatch(): No splitStrategy configured ******");
            return;
        }
        request.setStatus(TaskStatus.NEW.getValue());
        taskService.save(request);
        List<SubTask> subTasks = splitStrategy.split(request, count);
        dispatcher.dispatchSubTasks(subTasks);
    }
}
