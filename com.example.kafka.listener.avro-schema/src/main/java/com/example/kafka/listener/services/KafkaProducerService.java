package com.example.kafka.listener.services;

import com.example.kafka.listener.avro.Notification;
import com.example.kafka.listener.models.NotificationModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, Notification> kafkaTemplate;

    @Value("${app.kafka.topics.notification-requests}")
    private String notificationRequestsTopic;

    public KafkaProducerService(KafkaTemplate<String, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(NotificationModel notification) {
        String topic = notificationRequestsTopic;

        Notification newNotification = Notification.newBuilder()
                .setRecipientId(notification.getRecipientId())
                .setChannel(notification.getChannel())
                .setMessage(notification.getMessage())
                .build();
        kafkaTemplate.send(topic, newNotification); // Round-robin partitioning
//        kafkaTemplate.send(topic, notification.getRecipientId(), newNotification);  // Key-based partitioning

        System.out.println("===== Send notification to [Orchestrator] =====");
        System.out.println(MessageFormat.format("Topic: {0}", topic));
        System.out.println(MessageFormat.format("RecipientID: {0}", notification.getRecipientId()));
        System.out.println(MessageFormat.format("Message: {0}", notification.getMessage()));
    }
}