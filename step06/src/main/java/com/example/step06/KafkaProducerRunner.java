package com.example.step06;

import com.example.step06.domain.Domain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducerRunner implements CommandLineRunner {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "inbound-topic1";

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 200000; i++) {
//            Thread.sleep(1000);
            String message = getMessage();
            kafkaTemplate.send(TOPIC, message);
        }

        log.info("Published 10 messages to Kafka");
    }

    private String getMessage() throws JsonProcessingException {
        Domain domain = Domain.builder()
                .name("junwoo")
                .age(34)
                .build();

        return objectMapper.writeValueAsString(domain);
    }

}
