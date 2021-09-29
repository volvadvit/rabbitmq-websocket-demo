package com.zuzex.vvolkov.service;

import com.zuzex.vvolkov.component.HandshakeInterceptor;
import com.zuzex.vvolkov.config.ClientMQConfig;
import com.zuzex.vvolkov.model.NumberDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientProducer {

    @Autowired private RabbitTemplate rabbitTemplate;
    @Autowired private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired private HandshakeInterceptor interceptor;

    public void run(NumberDTO input) {
        int request = Integer.parseInt(input.getNumber());
        NumberDTO message = new NumberDTO();

        for (int i = 0; i < request; i++) {
            System.err.println("Sending number in queue: " + i);

            Integer response = (Integer) rabbitTemplate.convertSendAndReceive(
                            ClientMQConfig.topicExchangeName,
                            "number", i);

            System.err.println("response: " + response);

            message.setNumber(response != null ? "Hello from server side:      " + response.toString() : null);

            //Response
            simpMessagingTemplate.convertAndSend("/topic/public", message);
        }
    }
}
