package com.example.step06.listener;

import com.example.step06.domain.Domain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private static final String TOPIC = "inbound-topic";
    private final ApplicationContext applicationContext;

    @ServiceActivator(inputChannel = TOPIC)
    public void handleKafkaMessage(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }

}
