package com.adg.scheduler.producers.misa.order;

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
 * Created on: 2022.03.11 11:42
 */
@Component
public class SaleOrderProducerService extends AbstractProducerService<SaleOrderWebClientService> {

    @Autowired
    private SaleOrderWebClientService saleOrderWebClientService;

    @Override
    protected SaleOrderWebClientService getWebClientService() {
        return this.saleOrderWebClientService;
    }

    @Override
    protected String getTopicName() {
        return PubSubConstants.Order.TOPIC_NAME;
    }

    @Override
    public void fetchThenProduce() {
        for (int i = 709; i >= 1; i--) {
            List<Map<String, Object>> items = this.getWebClientService().fetchItems(i);
            System.out.printf("Topic: %s -- Item Size: %s -- Page: %s\n", this.getTopicName(), items.size(), i);
            items.stream()
                    .map(JsonUtils::toJson)
                    .collect(Collectors.toList())
                    .forEach(item -> kafkaTemplate.send(this.getTopicName(), item));
        }

    }
}
