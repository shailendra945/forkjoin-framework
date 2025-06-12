package com.infoiv.async.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "forkjoin")
public class ForkJoinProperties {

    private int subtaskQueueCount;
    private String joinQueue;
    private Map<String, String> queueProcessorMap;
    private RetryProperties retry;
    private NotificationProperties notifications;
    private Strategies strategies;

    public int getSubtaskQueueCount() {
        return subtaskQueueCount;
    }

    public void setSubtaskQueueCount(int subtaskQueueCount) {
        this.subtaskQueueCount = subtaskQueueCount;
    }

    public String getJoinQueue() {
        return joinQueue;
    }

    public void setJoinQueue(String joinQueue) {
        this.joinQueue = joinQueue;
    }

    public Map<String, String> getQueueProcessorMap() {
        return queueProcessorMap;
    }

    public void setQueueProcessorMap(Map<String, String> queueProcessorMap) {
        this.queueProcessorMap = queueProcessorMap;
    }

    public RetryProperties getRetry() {
        return retry;
    }

    public void setRetry(RetryProperties retry) {
        this.retry = retry;
    }

    public NotificationProperties getNotifications() {
        return notifications;
    }

    public void setNotifications(NotificationProperties notifications) {
        this.notifications = notifications;
    }

    public Strategies getStrategies() {
        return strategies;
    }

    public void setStrategies(Strategies strategies) {
        this.strategies = strategies;
    }

    public static class RetryProperties {
        private int maxAttempts;
    }

    public static class NotificationProperties {
        private String webhookUrl;
    }

    public static class Strategies{
        private String split;
        private String join;

        public String getSplit() {
            return split;
        }

        public void setSplit(String split) {
            this.split = split;
        }

        public String getJoin() {
            return join;
        }

        public void setJoin(String join) {
            this.join = join;
        }
    }
}
