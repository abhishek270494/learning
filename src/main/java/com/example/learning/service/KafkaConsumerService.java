package com.example.learning.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class KafkaConsumerService {

    private final List<String> messages = Collections.synchronizedList(new ArrayList<>());

    @KafkaListener(topics = "my-topic", groupId = "test-group")
    public void listen(String message) {
        messages.add(message);
        System.out.println("Received message: " + message);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void clearMessages() {
        messages.clear();
    }
}