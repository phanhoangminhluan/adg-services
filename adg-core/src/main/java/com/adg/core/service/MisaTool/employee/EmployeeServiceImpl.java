package com.adg.core.service.MisaTool.employee;

import com.adg.core.mapper.EmployeeMapper;
import com.adg.core.model.employee.EmployeeDTO;
import com.adg.core.model.employee.EmployeeEntity;
import com.adg.core.repository.employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.01 22:49
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void save(EmployeeDTO dto) {
        EmployeeEntity entity = this.employeeMapper.toEntity(dto);
        EmployeeEntity savedEntity = this.repository.save(entity);
        System.out.println(savedEntity.getAsyncId());
    }
}
