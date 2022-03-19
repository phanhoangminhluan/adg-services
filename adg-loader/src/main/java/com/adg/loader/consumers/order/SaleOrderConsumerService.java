package com.adg.loader.consumers.order;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.core.model.order.OrderDTO;
import com.adg.core.service.order.OrderService;
import com.adg.loader.consumers.AbstractConsumerService;
import com.merlin.asset.core.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 11:52
 */
@Component
public class SaleOrderConsumerService extends AbstractConsumerService {

    @Autowired
    private OrderService orderService;
    @KafkaListener(
            topics = PubSubConstants.Order.TOPIC_NAME,
            containerFactory = PubSubConstants.Order.LISTENER_CONTAINER_FACTORY
    )
    @Override
    public void consume(String message) {
        OrderDTO dto = JsonUtils.fromJson(message, OrderDTO.class);
        orderService.save(dto);
    }

}
