package com.infoiv.async.repos;

import com.infoiv.async.entities.JoinResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface JoinResultRepository extends JpaRepository<JoinResultEntity,String> {
    void deleteAllByJoinedAtBefore(LocalDateTime cutoff);
}
