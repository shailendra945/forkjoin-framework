package com.infoiv.async.repos;

import com.infoiv.async.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity,String> {
}
