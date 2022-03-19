package com.adg.loader.consumers.product;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.core.model.product.ProductDTO;
import com.adg.core.service.product.ProductService;
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
public class ProductConsumerService extends AbstractConsumerService {

    @Autowired
    private ProductService productService;

    @KafkaListener(
            topics = PubSubConstants.Product.TOPIC_NAME,
            containerFactory = PubSubConstants.Product.LISTENER_CONTAINER_FACTORY
    )
    @Override
    public void consume(String message) {
        ProductDTO dto = JsonUtils.fromJson(message, ProductDTO.class);
        productService.save(dto);
    }
}
