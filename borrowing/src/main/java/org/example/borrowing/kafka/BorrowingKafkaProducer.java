package org.example.borrowing.kafka;

import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
public class BorrowingKafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(BorrowingKafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    //@Value("${spring.kafka.topic.borrowing:borrowing-events}")
    private String topic;

    public BorrowingKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBorrowingDeleteEvent(Long borrowingId){
        String event = String.format("{\"event\":\"BORROWING_DELETED\",\"borrowingId\":\"%s\"}", borrowingId);
        logger.info("Producing borrowing deleted event: {} ",event);
        kafkaTemplate.send(topic,event);
    }
}
