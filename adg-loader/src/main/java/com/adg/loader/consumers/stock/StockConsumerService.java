package com.adg.loader.consumers.stock;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.loader.consumers.AbstractConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 23:35
 */
@Component
public class StockConsumerService extends AbstractConsumerService {

    @KafkaListener(
            topics = PubSubConstants.Stock.TOPIC_NAME,
            containerFactory = PubSubConstants.Stock.LISTENER_CONTAINER_FACTORY
    )
    @Override
    public void consume(String message) {
        super.consume(message);
    }

}