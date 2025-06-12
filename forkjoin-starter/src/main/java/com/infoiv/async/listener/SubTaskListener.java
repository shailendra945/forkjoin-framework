package com.infoiv.async.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoiv.async.api.NotificationSender;
import com.infoiv.async.api.ProcessStrategy;
import com.infoiv.async.core.JoinCoordinator;
import com.infoiv.async.core.TaskDispatcher;
import com.infoiv.async.entities.SubTaskEntity;
import com.infoiv.async.mapper.SubTaskMapper;
import com.infoiv.async.models.SubTaskResult;
import com.infoiv.async.repos.SubTaskRepository;
import com.infoiv.async.repos.SubTaskResultRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SubTaskListener {

    @Autowired
    private SubTaskRepository subTaskRepository;
    @Autowired
    private SubTaskResultRepository subTaskResultRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NotificationSender notificationSender;

    private final JoinCoordinator joinCoordinator;
    private final TaskDispatcher taskDispatcher;



    public SubTaskListener(JoinCoordinator joinCoordinator, TaskDispatcher taskDispatcher) {
        this.joinCoordinator = joinCoordinator;
        this.taskDispatcher = taskDispatcher;
    }

    @RabbitListener(queues = {"subtask-1", "subtask-2", "subtask-3"})
    public void processSubtask(SubTaskEntity subTaskEntity) {
        try {
            subTaskEntity.setStatus(SubTaskEntity.Status.PROCESSING);
            subTaskEntity.setUpdatedAt(LocalDateTime.now());
            subTaskRepository.save(subTaskEntity);
            //save task inprogress
            // Process
            ProcessStrategy processStrategy =(ProcessStrategy) applicationContext.getBean(subTaskEntity.getProcessStrategy());
            SubTaskResult result = processStrategy.process(SubTaskMapper.INSTANCE.toModel(subTaskEntity));
            subTaskEntity.setStatus(SubTaskEntity.Status.SUCCESS);
            subTaskEntity.setResult(objectMapper.writeValueAsString(result));
            // Send to join
            taskDispatcher.dispatchResultToJoinQueue(result);
        } catch (Exception ex) {
            subTaskEntity.setStatus(SubTaskEntity.Status.RETRY);
            //task retry
        } finally {
            subTaskEntity.setUpdatedAt(LocalDateTime.now());
            subTaskRepository.save(subTaskEntity);
        }
    }


}
