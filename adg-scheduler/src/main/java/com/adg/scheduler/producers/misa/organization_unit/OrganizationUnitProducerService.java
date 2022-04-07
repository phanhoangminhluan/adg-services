package com.adg.scheduler.producers.misa.organization_unit;

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
public class OrganizationUnitProducerService extends AbstractProducerService<OrganizationUnitWebClientService> {

    @Autowired
    private OrganizationUnitWebClientService organizationUnitWebClientService;

    @Override
    protected OrganizationUnitWebClientService getWebClientService() {
        return this.organizationUnitWebClientService;
    }

    @Override
    protected String getTopicName() {
        return PubSubConstants.OrganizationUnit.TOPIC_NAME;
    }

    @Override
    public void fetchThenProduce() {
        int page = 1;
        int size;
        List<Map<String, Object>> items = this.getWebClientService().fetchItems(page);
        System.out.printf("Topic: %s -- Item Size: %s -- Page: %s\n", this.getTopicName(), items.size(), page);
        page++;
        size = items.size();
        items.stream()
                .map(JsonUtils::toJson)
                .collect(Collectors.toList())
                .forEach(item -> kafkaTemplate.send(this.getTopicName(), item));

    }
}
