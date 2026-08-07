package com.scode.User_Management.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-events", groupId = "user-management-group")
    public void consumeUserCreatedEvent(UserCreatedEvent event){

        log.info("=====================================");
        log.info("Received User Created Event");
        log.info("Id : {}",event.getId());
        log.info("Name : {}",event.getName());
        log.info("Email : {}",event.getEmail());
        log.info("=====================================");
    }
}
