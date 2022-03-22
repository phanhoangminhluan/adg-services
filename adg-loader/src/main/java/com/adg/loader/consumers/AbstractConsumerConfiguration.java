package com.adg.loader.consumers;

import com.adg.core.service.slack.SlackService;
import com.merlin.asset.core.utils.MapUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.stereotype.Component;


/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 23:25
 */
@Component
public abstract class AbstractConsumerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    protected String bootstrapServer;

    @Autowired
    private SlackService slackService;


    private final static Logger logger = LoggerFactory.getLogger(AbstractConsumerConfiguration.class);

    protected abstract String getGroupId();

    protected ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(MapUtils.ImmutableMap()
                .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServer)
                .put(ConsumerConfig.GROUP_ID_CONFIG, this.getGroupId())
                .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .build()
        );
    }

    protected ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }

    protected CommonErrorHandler errorHandler(ConsumerRecordRecoverer recoverer) {

        DefaultErrorHandler defaultErrorHandler = new DefaultErrorHandler(recoverer);
        defaultErrorHandler.addNotRetryableExceptions(IllegalArgumentException.class);
        defaultErrorHandler.setAckAfterHandle(true);
        return defaultErrorHandler;
    }



}
