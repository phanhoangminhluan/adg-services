package com.adg.core.mapper;

import com.adg.core.model.product.ProductEntity;
import com.merlin.mapper.MerlinMapper;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.18 22:04
 */
public class ProductMapper extends MerlinMapper<ProductEntity> {
    public ProductMapper(Class<ProductEntity> entityClass) {
        super(entityClass);
    }
}
