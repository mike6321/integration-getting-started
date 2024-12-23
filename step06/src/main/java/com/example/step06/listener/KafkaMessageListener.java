package com.example.step06.listener;

import com.example.step06.domain.Domain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final ApplicationContext applicationContext;

    @ServiceActivator(inputChannel = "inbound-topic1", poller = @Poller(fixedRate = "30", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }

    @ServiceActivator(inputChannel = "inbound-topic2", poller = @Poller(fixedRate = "30", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage2(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }

    @ServiceActivator(inputChannel = "inbound-topic3", poller = @Poller(fixedRate = "30", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage3(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }

    @ServiceActivator(inputChannel = "inbound-topic4", poller = @Poller(fixedRate = "30", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage4(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }

    @ServiceActivator(inputChannel = "inbound-topic5", poller = @Poller(fixedRate = "30", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage5(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }

    @ServiceActivator(inputChannel = "inbound-topic6", poller = @Poller(fixedRate = "30", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage6(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }

    @ServiceActivator(inputChannel = "inbound-topic7", poller = @Poller(fixedRate = "30", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage7(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }
    @ServiceActivator(inputChannel = "inbound-topic8", poller = @Poller(fixedRate = "30", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage8(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }
    @ServiceActivator(inputChannel = "inbound-topic9", poller = @Poller(fixedRate = "30", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage9(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }
    @ServiceActivator(inputChannel = "inbound-topic10", poller = @Poller(fixedRate = "30", taskExecutor = "inBoundTaskExecutor"))
    public void handleKafkaMessage10(Domain message) {
        log.info("Received message: {}", message);

        var messageChannel = (MessageChannel) applicationContext.getBean("kafkaOutBoundEventHandler");
        messageChannel.send(new GenericMessage<>(message));
    }

}
