package com.zuzex.vvolkov.listener;

import com.zuzex.vvolkov.config.ServerMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = ServerMQConfig.serverQueueName)
    public int receiveMessageFromFirst(int input) throws Exception {

        System.err.println("Get number: " + input);

        //Producer
        Thread.sleep(3000);
        int outputNumber = input + 2;


        System.err.println("Send output: " + outputNumber);
        return outputNumber;
    }
}
