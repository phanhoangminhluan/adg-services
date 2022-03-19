package com.adg.core.repository.order;

import com.adg.core.model.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.19 03:02
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
