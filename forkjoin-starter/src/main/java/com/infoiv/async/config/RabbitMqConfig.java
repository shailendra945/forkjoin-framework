package com.infoiv.async.config;

import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RabbitMqConfig {

    @Autowired
    private ForkJoinProperties properties;

    @Bean
    public Declarables queues() {
        List<Declarable> declarables = new ArrayList<>();
        for (int i = 1; i <= properties.getSubtaskQueueCount(); i++) {
            String queueName = "subtask-" + i;
            declarables.add(new Queue(queueName));
        }
        declarables.add(new Queue(properties.getJoinQueue()));
        return new Declarables(declarables);
    }
}
