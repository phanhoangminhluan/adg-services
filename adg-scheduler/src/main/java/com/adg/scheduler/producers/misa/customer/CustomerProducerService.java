package com.adg.scheduler.producers.misa.customer;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.scheduler.producers.misa.AbstractProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 11:46
 */
@Component
public class CustomerProducerService extends AbstractProducerService<CustomerWebClientService> {

    @Autowired
    private CustomerWebClientService customerWebClientService;

    @Override
    protected CustomerWebClientService getWebClientService() {
        return this.customerWebClientService;
    }

    @Override
    protected String getTopicName() {
        return PubSubConstants.Customer.TOPIC_NAME;
    }

}
