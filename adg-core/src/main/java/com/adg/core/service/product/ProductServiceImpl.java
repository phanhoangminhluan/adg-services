package com.adg.core.service.product;

import com.adg.core.mapper.ProductMapper;
import com.adg.core.model.product.ProductDTO;
import com.adg.core.model.product.ProductEntity;
import com.adg.core.repository.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.18 20:45
 */
@Component
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public void save(ProductDTO dto) {
        ProductEntity entity = mapper.toEntity(dto);
        ProductEntity savedEntity = repository.save(entity);
    }
}
