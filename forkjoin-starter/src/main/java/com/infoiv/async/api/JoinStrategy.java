package com.infoiv.async.api;

import com.infoiv.async.models.SubTaskResult;

import java.util.List;

public interface JoinStrategy {

    public Object join(List<SubTaskResult> results);
}
