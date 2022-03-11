package com.adg.scheduler.producers.misa.order;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.scheduler.producers.misa.AbstractProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
