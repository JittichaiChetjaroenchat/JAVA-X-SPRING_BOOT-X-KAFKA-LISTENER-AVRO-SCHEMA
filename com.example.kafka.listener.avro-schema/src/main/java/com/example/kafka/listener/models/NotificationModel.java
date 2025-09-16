package com.example.kafka.listener.models;

import lombok.Data;

@Data
public class NotificationModel {
    private String recipientId;
    private String channel;
    private String message;
}