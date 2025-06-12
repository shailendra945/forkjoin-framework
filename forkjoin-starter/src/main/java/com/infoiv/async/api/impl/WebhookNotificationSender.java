package com.infoiv.async.api.impl;

import com.infoiv.async.api.NotificationSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebhookNotificationSender implements NotificationSender {

    @Value("${notifications.webhook-url}")
    private String webhookUrl;

    private final RestTemplate restTemplate;

    public WebhookNotificationSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendNotification(String taskId, Object result) {
        restTemplate.postForEntity(webhookUrl, result, Void.class);
    }
}
