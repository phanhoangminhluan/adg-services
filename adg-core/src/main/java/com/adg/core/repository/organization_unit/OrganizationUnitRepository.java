package com.adg.core.repository.organization_unit;

import com.adg.core.model.organization_unit.OrganizationUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.01 23:49
 */
@Repository
public interface OrganizationUnitRepository extends JpaRepository<OrganizationUnitEntity, Integer> {
}
