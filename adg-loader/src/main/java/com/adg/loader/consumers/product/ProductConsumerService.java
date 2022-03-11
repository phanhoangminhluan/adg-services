package com.adg.loader.consumers.product;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.loader.consumers.AbstractConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 11:52
 */
@Component
public class ProductConsumerService extends AbstractConsumerService {

    @KafkaListener(
            topics = PubSubConstants.Product.TOPIC_NAME,
            containerFactory = PubSubConstants.Product.LISTENER_CONTAINER_FACTORY
    )
    @Override
    public void consume(String message) {
        super.consume(message);
    }

}
