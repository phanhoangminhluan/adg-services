package com.adg.scheduler.producers.misa;

import com.merlin.asset.core.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 01:27
 */
@Component
public abstract class AbstractProducerService<T extends AbstractWebClientService> {

    @Autowired
    protected KafkaTemplate<String, String> kafkaTemplate;

    protected abstract T getWebClientService();

    protected abstract String getTopicName();

    public void fetchThenProduce() {
        int page = 1;
        int size;
        do {
            List<Map<String, Object>> items = this.getWebClientService().fetchItems(page);
            System.out.printf("Topic: %s -- Item Size: %s -- Page: %s\n", this.getTopicName(), items.size(), page);
            page++;
            size = items.size();
            items.stream()
                    .map(JsonUtils::toJson)
                    .collect(Collectors.toList())
                    .forEach(item -> kafkaTemplate.send(this.getTopicName(), item));

        } while (size != 0);
    }

}
