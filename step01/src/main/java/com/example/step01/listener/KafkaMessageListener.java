package com.example.step01.listener;

import com.example.step01.domain.Domain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaMessageListener {

    @ServiceActivator(inputChannel = "kafkaInboundEventChannel")
    public void handleKafkaMessage(Message<Domain> message) {
        Domain payload = message.getPayload();
        log.info("Received message: {}", payload);
    }

}
