package com.example.kafka.listener.services;

import com.example.kafka.listener.avro.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "${app.kafka.topics.notification-requests}", groupId = "${app.kafka.group}")
    public void consume(Notification notification) {
        String recipientId = notification.getRecipientId().toString();
        String channel = notification.getChannel().toString();
        String message = notification.getMessage().toString();

        System.out.println("===== Receive notification to [Orchestrator] =====");
        System.out.println(MessageFormat.format("RecipientID: {0}", recipientId));
        System.out.println(MessageFormat.format("Channel: {0}", channel));
        System.out.println(MessageFormat.format("Message: {0}", message));
    }
}