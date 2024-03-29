package com.tweetapp.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "my_topic", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info("Kafka Consumer:" + message);
    }
}