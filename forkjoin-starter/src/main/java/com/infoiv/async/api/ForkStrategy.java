package com.infoiv.async.api;

import com.infoiv.async.models.SubTask;
import com.infoiv.async.models.TaskRequest;

import java.util.List;

public interface ForkStrategy {
    List<SubTask> fork(TaskRequest taskRequest);
}
