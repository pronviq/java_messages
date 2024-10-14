package ru.max.messages.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	// @KafkaListener(topics = "ping", groupId = "my_cons")
	// public void listen(String msg) {
	// 	System.out.println("RECEIVED MESSAGE!! " + msg);
	// }
}

