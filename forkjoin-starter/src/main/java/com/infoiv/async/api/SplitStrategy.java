package com.infoiv.async.api;

import com.infoiv.async.models.SubTask;
import com.infoiv.async.models.TaskRequest;

import java.util.List;

public interface SplitStrategy {

    public List<SubTask> split(TaskRequest request, int subTaskCount);
}
