package com.adg.loader.consumers.stock;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.core.model.stock.StockDTO;
import com.adg.core.service.stock.StockService;
import com.adg.loader.consumers.AbstractConsumerService;
import com.merlin.asset.core.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 23:35
 */
@Component
public class StockConsumerService extends AbstractConsumerService {

    @Autowired
    private StockService stockService;

    @KafkaListener(
            topics = PubSubConstants.Stock.TOPIC_NAME,
            containerFactory = PubSubConstants.Stock.LISTENER_CONTAINER_FACTORY
    )
    @Override
    public void consume(String message) {
        StockDTO stockDTO = JsonUtils.fromJson(message, StockDTO.class);
        stockService.save(stockDTO);
    }

}