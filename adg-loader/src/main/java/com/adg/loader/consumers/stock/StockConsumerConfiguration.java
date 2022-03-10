package com.adg.loader.consumers.stock;

import com.adg.core.common.enums.Topic;
import com.adg.core.common.constants.ConsumerConstants;
import com.adg.loader.consumers.AbstractConsumerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 23:10
 */
@Configuration
@EnableKafka
public class StockConsumerConfiguration extends AbstractConsumerConfiguration {

    @Override
    protected Topic getTopic() {
        return Topic.STOCK;
    }

    @Override
    @Bean(value = ConsumerConstants.STOCK_CONSUMER_FACTORY)
    protected ConsumerFactory<String, String> consumerFactory() {
        return super.consumerFactory();
    }

    @Override
    @Bean(value = ConsumerConstants.STOCK_LISTENER_CONTAINER_FACTORY)
    protected ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {
        return super.concurrentKafkaListenerContainerFactory();
    }
}
