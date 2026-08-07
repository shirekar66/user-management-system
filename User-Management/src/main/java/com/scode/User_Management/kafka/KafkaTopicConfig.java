package com.scode.User_Management.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String USER_TOPIC="user-events";

    public NewTopic userTopic(){

        return TopicBuilder.name(USER_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
