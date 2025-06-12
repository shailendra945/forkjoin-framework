package com.infoiv.async.repos;

import com.infoiv.async.entities.SubTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTaskEntity,String> {
    
    List<SubTaskEntity> findByStatusAndAttemptCountLessThan(SubTaskEntity.Status status, int maxAttempts);

    void deleteAllByStatusAndUpdatedAtBefore(SubTaskEntity.Status status, LocalDateTime cutoff);

    List<SubTaskEntity> findByTaskId(String taskId);

    long countByTaskId(String taskId);
}
