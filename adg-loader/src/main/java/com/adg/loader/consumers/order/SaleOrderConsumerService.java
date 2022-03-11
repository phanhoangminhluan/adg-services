package com.adg.loader.consumers.order;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.loader.consumers.AbstractConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 11:52
 */
@Component
public class SaleOrderConsumerService extends AbstractConsumerService {

    @KafkaListener(
            topics = PubSubConstants.Order.TOPIC_NAME,
            containerFactory = PubSubConstants.Order.LISTENER_CONTAINER_FACTORY
    )
    @Override
    public void consume(String message) {
        super.consume(message);
    }

}
