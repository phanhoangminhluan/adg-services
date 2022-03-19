package com.adg.core.service.customer;

import com.adg.core.mapper.CustomerMapper;
import com.adg.core.model.customer.CustomerDTO;
import com.adg.core.model.customer.CustomerEntity;
import com.adg.core.repository.customer.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.18 20:45
 */
@Component
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;

    @Override
    public void save(CustomerDTO dto) {
        CustomerEntity entity = mapper.toEntity(dto);
        CustomerEntity savedEntity = repository.save(entity);
//        logger.info("Saved Entity: " + savedEntity);
    }
}
