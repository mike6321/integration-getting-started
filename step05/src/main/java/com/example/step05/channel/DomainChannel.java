package com.example.step05.channel;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class DomainChannel {

    @Bean(name = "inbound-topic")
    public PublishSubscribeChannel inBoundChannel(ThreadPoolTaskExecutor pubSubTaskExecutor) {
        return new PublishSubscribeChannel(pubSubTaskExecutor);
    }
}
