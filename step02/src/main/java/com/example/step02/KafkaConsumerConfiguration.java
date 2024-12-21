package com.example.step02;

import com.example.step02.domain.Domain;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.integration.router.ExpressionEvaluatingRouter;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.messaging.PollableChannel;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfiguration {

    private final KafkaProperties kafkaProperties;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "inbound-topic";
    private static final String GROUP_ID = "inbound-topic-group";

    @Bean
    @Router(inputChannel = "kafkaInboundEventChannel")
    public ExpressionEvaluatingRouter kafkaInboundEventChannelRouter() {
        return new ExpressionEvaluatingRouter("headers.kafka_receivedTopic");
    }

    @Bean(name = "kafkaInboundEventChannel")
    public PollableChannel kafkaInboundEventChannel() {
        return new QueueChannel();
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, String> kafkaListenerContainer(ConsumerFactory<String, String> consumerFactory) {
        String[] topics = {TOPIC};
        ContainerProperties containerProps = new ContainerProperties(topics);
        containerProps.setGroupId(GROUP_ID);

        ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(consumerFactory, containerProps);
        container.setConcurrency(3);

        return container;
    }

    @Bean
    public DefaultKafkaMessageConverter messageConverter() {
        // 토픽과 클래스 매핑 예시
        Map<String, Class<?>> topicMap = Map.of(TOPIC, Domain.class);
        return new DefaultKafkaMessageConverter(objectMapper, topicMap);
    }

    @Bean
    public KafkaMessageDrivenChannelAdapter<String, String> kafkaMessageAdapter(
            ConcurrentMessageListenerContainer<String, String> kafkaListenerContainer,
            PollableChannel kafkaInboundEventChannel,
            DefaultKafkaMessageConverter defaultKafkaMessageConverter) {

        KafkaMessageDrivenChannelAdapter<String, String> adapter = new KafkaMessageDrivenChannelAdapter<>(kafkaListenerContainer);
        adapter.setOutputChannel(kafkaInboundEventChannel);
        adapter.setMessageConverter(defaultKafkaMessageConverter);
        return adapter;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory consumerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props);
    }

}
