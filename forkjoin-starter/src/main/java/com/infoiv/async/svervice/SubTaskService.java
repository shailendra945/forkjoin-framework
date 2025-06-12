package com.infoiv.async.svervice;

import com.infoiv.async.entities.SubTaskEntity;
import com.infoiv.async.mapper.SubTaskMapper;
import com.infoiv.async.models.SubTask;
import com.infoiv.async.repos.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubTaskService {

    @Autowired
    private SubTaskRepository subTaskRepository;

    public SubTask save(SubTask subTask){
        SubTaskEntity subTaskEntity = SubTaskMapper.INSTANCE.toEntity(subTask);
        subTaskEntity = subTaskRepository.save(subTaskEntity);
        return SubTaskMapper.INSTANCE.toModel(subTaskEntity);
    }

    public long getSubTaskCountByTaskId(String taskId){
        return subTaskRepository.countByTaskId(taskId);
    }
}
