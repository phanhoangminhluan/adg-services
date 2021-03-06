package com.adg.core.repository.customer;

import com.adg.core.model.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.18 22:10
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
}
