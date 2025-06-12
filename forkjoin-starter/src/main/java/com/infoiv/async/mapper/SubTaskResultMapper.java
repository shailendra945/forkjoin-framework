package com.infoiv.async.mapper;

import com.infoiv.async.entities.SubTaskResultEntity;
import com.infoiv.async.models.SubTaskResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubTaskResultMapper {

    SubTaskResultMapper INSTANCE = Mappers.getMapper(SubTaskResultMapper.class);

    SubTaskResultEntity toEntity(SubTaskResult subTaskResult);

    SubTaskResult toModel(SubTaskResultEntity subTaskResultEntity);
}
