package com.adg.core.mapper;

import com.adg.core.model.employee.EmployeeEntity;
import com.merlin.mapper.MerlinMapper;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.01 23:09
 */
public class EmployeeMapper extends MerlinMapper<EmployeeEntity> {
    public EmployeeMapper(Class<EmployeeEntity> entityClass) {
        super(entityClass);
    }
}
