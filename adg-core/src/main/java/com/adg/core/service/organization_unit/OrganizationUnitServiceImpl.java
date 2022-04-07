package com.adg.core.service.organization_unit;

import com.adg.core.mapper.OrganizationUnitMapper;
import com.adg.core.model.organization_unit.OrganizationUnitDTO;
import com.adg.core.model.organization_unit.OrganizationUnitEntity;
import com.adg.core.repository.organization_unit.OrganizationUnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.01 23:50
 */
@Service
public class OrganizationUnitServiceImpl implements OrganizationUnitService{

    private static final Logger logger = LoggerFactory.getLogger(OrganizationUnitServiceImpl.class);

    @Autowired
    private OrganizationUnitRepository repository;

    @Autowired
    private OrganizationUnitMapper mapper;

    @Override
    public void save(OrganizationUnitDTO dto) {
        OrganizationUnitEntity entity = mapper.toEntity(dto);
        OrganizationUnitEntity savedEntity = repository.save(entity);
    }
}
