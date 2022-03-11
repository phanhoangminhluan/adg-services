package com.adg.loader.consumers.customer;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.loader.consumers.AbstractConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 11:52
 */
@Component
public class CustomerConsumerService extends AbstractConsumerService {

    @KafkaListener(
            topics = PubSubConstants.Customer.TOPIC_NAME,
            containerFactory = PubSubConstants.Customer.LISTENER_CONTAINER_FACTORY
    )
    @Override
    public void consume(String message) {
       super.consume(message);
    }

}
