package com.infoiv.async.mapper;


import com.infoiv.async.entities.TaskEntity;
import com.infoiv.async.models.TaskRequest;
import org.mapstruct.factory.Mappers;

public interface TaskRequestMapper {

    TaskRequestMapper INSTANCE = Mappers.getMapper(TaskRequestMapper.class);

    TaskEntity toEntity(TaskRequest taskRequest);

    TaskRequest toModel(TaskEntity askEntity);
}
