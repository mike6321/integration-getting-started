package com.example.step04.listener;

import com.example.step04.domain.Domain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.endpoint.AbstractPollingEndpoint;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.Trigger;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private static final String TOPIC = "inbound-topic";
    private final ApplicationContext applicationContext;

    /**
     * @see AbstractPollingEndpoint#doStart()
     * @see org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler#schedule(Runnable, Trigger)
     * @see AbstractPollingEndpoint#createPoller()
     * */
    @ServiceActivator(inputChannel = TOPIC, poller = @Poller(fixedRate = "200", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }

}
