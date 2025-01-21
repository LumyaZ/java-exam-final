package org.example.book.kafka;

import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class BookKafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(BookKafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    //@Value("${spring.kafka.topic.book:book-events}")
    private String topic;

    public BookKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBookDeleteEvent(Long bookId){
        String event = String.format("{\"event\":\"BOOK_DELETED\",\"bookId\":\"%s\"}", bookId);
        logger.info("Producing book deleted event: {} ",event);
        kafkaTemplate.send(topic,event);
    }
}
