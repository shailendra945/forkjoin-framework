package com.infoiv.async.scheduler;

import com.infoiv.async.entities.SubTaskEntity;
import com.infoiv.async.repos.JoinResultRepository;
import com.infoiv.async.repos.SubTaskRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SubTaskRetryScheduler {

    @Autowired
    private SubTaskRepository subTaskRepository;

    @Autowired
    private JoinResultRepository joinResultRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${forkjoin.retry.max-attempts:3}")
    private int maxAttempts;

    @Scheduled(fixedRate = 60000)
    public void retryFailedTasks() {
        List<SubTaskEntity> retryable = subTaskRepository.findByStatusAndAttemptCountLessThan(
                SubTaskEntity.Status.RETRY, maxAttempts
        );

        for (SubTaskEntity entity : retryable) {
            entity.setAttemptCount(entity.getAttemptCount() + 1);
            entity.setUpdatedAt(LocalDateTime.now());
            subTaskRepository.save(entity);

            rabbitTemplate.convertAndSend(entity.getQueueName(), entity);
        }
    }

    @Scheduled(cron = "0 0 2 * * *") // Daily at 2am
    public void cleanupOldData() {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(7);
        subTaskRepository.deleteAllByStatusAndUpdatedAtBefore(SubTaskEntity.Status.SUCCESS, cutoff);
        joinResultRepository.deleteAllByJoinedAtBefore(cutoff);
    }

}
