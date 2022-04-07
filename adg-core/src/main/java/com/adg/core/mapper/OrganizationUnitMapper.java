package com.adg.core.mapper;

import com.adg.core.model.organization_unit.OrganizationUnitEntity;
import com.merlin.mapper.MerlinMapper;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.01 23:51
 */
public class OrganizationUnitMapper extends MerlinMapper<OrganizationUnitEntity> {
    public OrganizationUnitMapper(Class<OrganizationUnitEntity> entityClass) {
        super(entityClass);
    }
}
