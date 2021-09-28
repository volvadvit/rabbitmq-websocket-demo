package com.zuzex.vvolkov.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerMQConfig {

    public static final String topicExchangeName = "main-exchange";
    public static final String serverQueueName = "main-queue";

    @Bean
    TopicExchange serverExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding mainBinding(TopicExchange exchange) {
        return BindingBuilder.bind(serverQueue()).to(exchange).with("number");
    }

    @Bean
    public Queue serverQueue() {
        return new Queue(serverQueueName);
    }
}
