package com.adg.core.mapper;

import com.adg.core.model.order_product.OrderProductEntity;
import com.merlin.mapper.MerlinMapper;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.19 03:05
 */
public class OrderProductMapper extends MerlinMapper<OrderProductEntity> {
    public OrderProductMapper(Class<OrderProductEntity> entityClass) {
        super(entityClass);
    }
}
