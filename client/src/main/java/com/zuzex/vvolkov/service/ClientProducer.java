package com.zuzex.vvolkov.service;

import com.zuzex.vvolkov.config.ClientMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ClientProducer implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        int request = Integer.parseInt(input);

        for (int i = 0; i < request; i++) {
            System.err.println("Sending number in queue: " + i);
            Integer response = (Integer) rabbitTemplate.convertSendAndReceive(
                            ClientMQConfig.topicExchangeName,
                            "number", i);

            System.err.println("response: " + response);
        }
    }
}
