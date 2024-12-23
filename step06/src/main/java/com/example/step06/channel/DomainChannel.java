package com.example.step06.channel;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class DomainChannel {

    @Bean(name = "inbound-topic1")
    public PollableChannel inBoundChannel1() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic2")
    public PollableChannel inBoundChannel2() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic3")
    public PollableChannel inBoundChannel3() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic4")
    public PollableChannel inBoundChannel4() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic5")
    public PollableChannel inBoundChannel5() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic6")
    public PollableChannel inBoundChannel6() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic7")
    public PollableChannel inBoundChannel7() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic8")
    public PollableChannel inBoundChannel8() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic9")
    public PollableChannel inBoundChannel9() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic10")
    public PollableChannel inBoundChannel10() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic11")
    public PollableChannel inBoundChannel11() {
        return new QueueChannel();
    }

    @Bean(name = "inbound-topic12")
    public PollableChannel inBoundChannel12() {
        return new QueueChannel();
    }

}
