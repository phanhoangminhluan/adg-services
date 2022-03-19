package com.adg.loader.consumers.customer;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.core.model.customer.CustomerDTO;
import com.adg.core.service.customer.CustomerService;
import com.adg.loader.consumers.AbstractConsumerService;
import com.merlin.asset.core.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 11:52
 */
@Component
public class CustomerConsumerService extends AbstractConsumerService {


    @Autowired
    private CustomerService customerService;

    @KafkaListener(
            topics = PubSubConstants.Customer.TOPIC_NAME,
            containerFactory = PubSubConstants.Customer.LISTENER_CONTAINER_FACTORY
    )
    @Override
    public void consume(String message) {
        try {
            CustomerDTO customerDTO = JsonUtils.fromJson(message, CustomerDTO.class);
            this.customerService.save(customerDTO);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println();
        }
    }

}
