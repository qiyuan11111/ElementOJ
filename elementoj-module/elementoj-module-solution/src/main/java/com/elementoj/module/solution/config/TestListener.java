package com.elementoj.module.solution.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestListener {

    @RabbitListener(queues = "eleQueue", concurrency = "1")
    public void test(Message message) throws InterruptedException {

    }

}
