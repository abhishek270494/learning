package com.example.learning.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.learning.service.KafkaConsumerService;
import com.example.learning.service.KafkaProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService producer;
    private final KafkaConsumerService consumer;

    // POST /kafka/send?message=Hello
    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        producer.sendMessage("my-topic", message);
        return "Message sent: " + message;
    }

    // GET /kafka/messages
    @GetMapping("/messages")
    public List<String> getMessages(@RequestParam(defaultValue = "false") boolean clear) {
        List<String> allMessages = consumer.getMessages();
        if (clear) {
            consumer.clearMessages();
        }
        return allMessages;
    }
}