package com.example.step02.listener;

import com.example.step02.domain.Domain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaMessageListener {

    private static final String TOPIC = "inbound-topic";

    @ServiceActivator(inputChannel = TOPIC)
    public void handleKafkaMessage(Domain message) {
        log.info("Received message: {}", message);
    }

}
