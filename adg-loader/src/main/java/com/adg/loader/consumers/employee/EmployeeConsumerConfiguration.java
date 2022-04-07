package com.adg.loader.consumers.employee;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.loader.consumers.AbstractConsumerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 11:52
 */
@Configuration
@EnableKafka
public class EmployeeConsumerConfiguration extends AbstractConsumerConfiguration {
    @Override
    protected String getGroupId() {
        return PubSubConstants.Employee.GROUP_ID;
    }

    @Override
    @Bean(value = PubSubConstants.Employee.CONSUMER_FACTORY)
    protected ConsumerFactory<String, String> consumerFactory() {
        return super.consumerFactory();
    }

    @Override
    @Bean(value = PubSubConstants.Employee.LISTENER_CONTAINER_FACTORY)
    protected ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {
        return super.concurrentKafkaListenerContainerFactory();
    }
}
