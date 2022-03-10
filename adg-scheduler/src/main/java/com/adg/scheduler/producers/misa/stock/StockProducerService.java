package com.adg.scheduler.producers.misa.stock;

import com.adg.core.common.constants.ConsumerConstants;
import com.merlin.asset.core.utils.JsonUtils;
import com.merlin.asset.core.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 23:59
 */
@Component
public class StockProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produce(List<Map<String, Object>> result) {
        result.forEach(map -> {
            String json = JsonUtils.toJson(map);
            System.out.printf("async_id: %s -- modified_date: %s --- Json: %s\n", MapUtils.getString(map, "async_id"), MapUtils.getString(map, "modified_date"), json);
            kafkaTemplate.send(ConsumerConstants.STOCK_TOPIC_NAME, json);
        });
    }
}
