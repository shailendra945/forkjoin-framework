package com.infoiv.async.svervice;

import com.infoiv.async.entities.TaskEntity;
import com.infoiv.async.mapper.TaskRequestMapper;
import com.infoiv.async.models.TaskRequest;
import com.infoiv.async.repos.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public TaskRequest save(TaskRequest taskRequest){
         TaskEntity taskEntity = TaskRequestMapper.INSTANCE.toEntity(taskRequest);
         taskEntity = taskRepository.save(taskEntity);
         return TaskRequestMapper.INSTANCE.toModel(taskEntity);

    }
}
