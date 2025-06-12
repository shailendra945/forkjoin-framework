package com.infoiv.async.core;

import com.infoiv.async.config.ForkJoinProperties;
import com.infoiv.async.controller.TaskController;
import com.infoiv.async.models.SubTask;
import com.infoiv.async.models.SubTaskResult;
import com.infoiv.async.repos.SubTaskRepository;
import com.infoiv.async.svervice.SubTaskService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskDispatcher {

    public static final Logger logger = LoggerFactory.getLogger(TaskDispatcher.class);

    @Autowired
    private ForkJoinProperties properties;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SubTaskService subTaskService;


    @Transactional
    public void dispatchSubTasks(List<SubTask> subTasks) {
        logger.info("********* Entered into dispatchSubTasks() *************** {}",subTasks);
        int queueCount = properties.getSubtaskQueueCount();
        for (int i = 0; i < subTasks.size(); i++) {
            String queueName = "subtask-" + (i % queueCount + 1);// round robin
            subTaskService.save(subTasks.get(i));
            rabbitTemplate.convertAndSend(queueName, subTasks.get(i));
        }
    }

    public void dispatchResultToJoinQueue(SubTaskResult result) {
        rabbitTemplate.convertAndSend(properties.getJoinQueue(), result);
    }
}
