package com.infoiv.async.repos;

import com.infoiv.async.entities.SubTaskEntity;
import com.infoiv.async.entities.SubTaskResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SubTaskResultRepository extends JpaRepository<SubTaskResultEntity,String> {

    List<SubTaskResultEntity> findByTaskId(String taskId);
}
