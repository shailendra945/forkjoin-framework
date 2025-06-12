package com.infoiv.async.api;

import com.infoiv.async.models.SubTask;
import com.infoiv.async.models.SubTaskResult;

public interface ProcessStrategy {

   public SubTaskResult process(SubTask subTask);
}
