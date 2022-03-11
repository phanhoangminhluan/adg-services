package com.adg.scheduler.producers.misa.product;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.scheduler.producers.misa.AbstractProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 01:20
 */
@Component
public class ProductProducerService extends AbstractProducerService<ProductWebClientService> {

    @Autowired
    private ProductWebClientService productWebClientService;

    @Override
    protected ProductWebClientService getWebClientService() {
        return this.productWebClientService;
    }

    @Override
    protected String getTopicName() {
        return PubSubConstants.Product.TOPIC_NAME;
    }
}
