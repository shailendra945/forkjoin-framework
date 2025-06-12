package com.infoiv.async.controller;

import com.infoiv.async.core.TaskForker;
import com.infoiv.async.models.TaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    public static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskForker taskForker;

    @PostMapping("/initiate/fork")
    public ResponseEntity<?> initiateForkJoin(@RequestBody TaskRequest taskRequest){
        logger.info("********* Entered into initiateFork() *************** {}",taskRequest);
        taskForker.forkAndDispatch(taskRequest);
        return ResponseEntity.ok(null);
    }

}
