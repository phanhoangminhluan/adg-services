package com.adg.scheduler.producers.misa.stock;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.scheduler.producers.misa.AbstractProducerService;
import com.merlin.asset.core.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 00:04
 */
@Component
public class StockProducerService extends AbstractProducerService<StockWebClientService> {

    @Autowired
    private StockWebClientService stockWebClientService;

    @Override
    protected StockWebClientService getWebClientService() {
        return this.stockWebClientService;
    }

    @Override
    protected String getTopicName() {
        return PubSubConstants.Stock.TOPIC_NAME;
    }

    @Override
    public void fetchThenProduce() {
        List<Map<String, Object>> items = this.getWebClientService().fetchItems(1);
        items.stream()
                .map(JsonUtils::toJson)
                .collect(Collectors.toList())
                .forEach(item -> kafkaTemplate.send(this.getTopicName(), item));

    }
}
