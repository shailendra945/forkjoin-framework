package com.infoiv.async.enums;

public enum TaskStatus {
    NEW("New"),
    IN_PROGRESS("InProgress"),
    COMPLETED("Completed"),
    FAILED("Failed"),
    RETRY("Retry");

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
