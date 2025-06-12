package com.infoiv.async.mapper;

import com.infoiv.async.entities.SubTaskEntity;
import com.infoiv.async.entities.SubTaskResultEntity;
import com.infoiv.async.models.SubTask;
import com.infoiv.async.models.SubTaskResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubTaskMapper {

    SubTaskMapper INSTANCE = Mappers.getMapper(SubTaskMapper.class);
    @Mapping(target = "queueName", ignore = true)
    @Mapping(target = "payload", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "attemptCount", ignore = true)
    @Mapping(target = "result", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SubTaskEntity toEntity(SubTask subTask);

    @Mapping(target = "data", ignore = true)
    @Mapping(target = "chunkData", ignore = true)
    SubTask toModel(SubTaskEntity subTaskEntity);
}
