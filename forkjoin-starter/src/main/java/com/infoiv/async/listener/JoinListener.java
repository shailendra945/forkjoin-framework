package com.infoiv.async.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoiv.async.api.JoinStrategy;
import com.infoiv.async.api.NotificationSender;
import com.infoiv.async.api.SplitStrategy;
import com.infoiv.async.core.JoinCoordinator;
import com.infoiv.async.entities.JoinResultEntity;
import com.infoiv.async.entities.SubTaskEntity;
import com.infoiv.async.entities.SubTaskResultEntity;
import com.infoiv.async.mapper.SubTaskMapper;
import com.infoiv.async.mapper.SubTaskResultMapper;
import com.infoiv.async.models.SubTask;
import com.infoiv.async.models.SubTaskResult;
import com.infoiv.async.repos.JoinResultRepository;
import com.infoiv.async.repos.SubTaskRepository;
import com.infoiv.async.repos.SubTaskResultRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JoinListener {

    @Autowired
    private JoinCoordinator joinCoordinator;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JoinResultRepository joinResultRepository;
    @Autowired
    private NotificationSender notificationSender;
    @Autowired
    private SubTaskResultRepository subTaskResultRepository;
    @Autowired
    private ApplicationContext applicationContext;

    @RabbitListener(queues = "${forkjoin.join-queue}")
    public void handleJoin(SubTaskResult result) {

        joinCoordinator.collectResult(result);
        if (joinCoordinator.isReadyToJoin(result.getTaskId())) {

            List<SubTaskResult> results = joinCoordinator.getResults(result.getTaskId());
            JoinStrategy joinStrategy = (JoinStrategy)
                    applicationContext.getBean(result.getJoinStrategy());
            Object joined = joinStrategy.join(results);
            JoinResultEntity joinResult = new JoinResultEntity();
            joinResult.setTaskId(result.getTaskId());
            try {
                joinResult.setFinalResult(objectMapper.writeValueAsString(joined));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            joinResult.setStatus(JoinResultEntity.Status.COMPLETED);
            joinResult.setJoinedAt(LocalDateTime.now());
            joinResultRepository.save(joinResult);
            notificationSender.sendNotification(result.getTaskId(), joined);
            joinCoordinator.deleteResults(result.getTaskId());
            // save task  as completed
        }
        else{
            // task inprogress
           // why not ready any subtask in retry status publish to subtask queue
        }
    }

    private SubTaskResult deserializeResult(SubTaskResultEntity subTaskResultEntity) {
       return SubTaskResultMapper.INSTANCE.toModel(subTaskResultEntity);
    }

}
