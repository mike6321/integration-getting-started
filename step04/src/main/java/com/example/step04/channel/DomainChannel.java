package com.example.step04.channel;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

@Component
public class DomainChannel {

    @Bean(name = "inbound-topic")
    public PollableChannel inBoundChannel() {
        return new QueueChannel();
    }
}
