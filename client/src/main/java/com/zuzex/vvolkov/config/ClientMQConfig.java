package com.zuzex.vvolkov.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientMQConfig {

    public static final String topicExchangeName = "main-exchange";
    public static final String clientQueueName = "main-queue";

    @Bean
    TopicExchange clientExchange() {
        return new TopicExchange(topicExchangeName);
    }
}
