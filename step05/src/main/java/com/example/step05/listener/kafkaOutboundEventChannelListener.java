package com.example.step05.listener;

import com.example.step05.domain.Domain;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class kafkaOutboundEventChannelListener {

    private static final String TOPIC = "outbound-topic";

    @ServiceActivator(inputChannel = "kafkaOutBoundEventHandler", outputChannel = "kafkaOutboundEventChannel")
    public Message<String> handle(Domain message) {
        log.info("Received message (kafkaOutboundEventChannelListener): {}", message);

        return MessageBuilder.withPayload(messageToJson(message))
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
    }

    private String messageToJson(Domain message) {
        try {
            return new ObjectMapper().writeValueAsString(message);
        } catch (IOException e) {
            log.error("Error", e);
            throw new RuntimeException(e);
        }
    }

}
