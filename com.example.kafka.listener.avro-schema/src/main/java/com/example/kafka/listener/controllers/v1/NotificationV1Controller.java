package com.example.kafka.listener.controllers.v1;

import com.example.kafka.listener.models.NotificationModel;
import com.example.kafka.listener.services.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationV1Controller {
    private final KafkaProducerService kafkaProducerService;

    public NotificationV1Controller(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public String Notify(@RequestBody NotificationModel notification) {
        kafkaProducerService.send(notification);

        return "Notification sent successfully";
    }
}