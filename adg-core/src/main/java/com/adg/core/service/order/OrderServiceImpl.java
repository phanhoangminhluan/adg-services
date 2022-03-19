package com.adg.core.service.order;

import com.adg.core.mapper.OrderMapper;
import com.adg.core.model.order.OrderDTO;
import com.adg.core.model.order.OrderEntity;
import com.adg.core.repository.order.OrderRepository;
import com.adg.core.repository.order_product.OrderProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.18 20:45
 */
@Component
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderMapper mapper;

    @Override
    public void save(OrderDTO dto) {
        try {
            OrderEntity entity = mapper.toEntityWithId(dto);
            OrderEntity savedEntity = orderRepository.save(entity);

            orderProductRepository.saveAll(entity.getOrderProducts());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
