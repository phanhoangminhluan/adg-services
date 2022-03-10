package com.adg.loader.consumers.stock;

import com.adg.core.common.constants.ConsumerConstants;
import com.merlin.asset.core.utils.JsonUtils;
import com.merlin.asset.core.utils.MapUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 23:35
 */
@Component
public class StockConsumerService {

    @KafkaListener(
            topics = ConsumerConstants.STOCK_TOPIC_NAME,
            containerFactory = ConsumerConstants.STOCK_LISTENER_CONTAINER_FACTORY
    )
    public void consume(String message) {

        Map<String, Object> map = JsonUtils.fromJson(message, JsonUtils.TYPE_TOKEN.MAP_STRING_OBJECT.type);
        System.out.printf("async_id: %s -- modified_date: %s\n", MapUtils.getString(map, "async_id"), MapUtils.getString(map, "modified_date"));


    }

}