package com.scode.User_Management.kafka;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserKafkaProducer {

    private final KafkaTemplate<String,UserCreatedEvent> kafkaTemplate;

    public void publishUserCreatedEvent(UserCreatedEvent event){

        log.info("Publishing UserCreatedEvent: {}",event);
        kafkaTemplate.send(KafkaTopicConfig.USER_TOPIC,event);
    }
}
