package com.senla.steshko.jms;

import com.senla.steshko.configurations.JmsConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = JmsConfig.QK_EXAMPLE_QUEUE)
    public void onMessageReceived(String message){
        System.out.println("Message received!: " + message);
    }
}
