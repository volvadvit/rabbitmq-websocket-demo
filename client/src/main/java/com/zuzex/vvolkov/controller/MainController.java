package com.zuzex.vvolkov.controller;

import com.zuzex.vvolkov.model.NumberDTO;
import com.zuzex.vvolkov.service.ClientProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @Autowired
    ClientProducer producer;

    @MessageMapping("/ws")
    @SendTo("topic/public")
    public NumberDTO inputNumberCheck(@Payload NumberDTO num) {
        System.err.println("CONTROLLER :: Get request: " + num.getNumber());
        return producer.run(num);
    }
}