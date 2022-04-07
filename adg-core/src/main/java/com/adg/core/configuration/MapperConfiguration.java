package com.adg.core.configuration;

import com.adg.core.mapper.*;
import com.adg.core.model.customer.CustomerEntity;
import com.adg.core.model.employee.EmployeeEntity;
import com.adg.core.model.order.OrderEntity;
import com.adg.core.model.order_product.OrderProductEntity;
import com.adg.core.model.organization_unit.OrganizationUnitEntity;
import com.adg.core.model.product.ProductEntity;
import com.adg.core.model.stock.StockEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.18 14:46
 */
@Configuration
public class MapperConfiguration {

    @Bean
    public StockMapper stockMapper() {
        return new StockMapper(StockEntity.class);
    }

    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper(ProductEntity.class);
    }

    @Bean
    public CustomerMapper customerMapper() {
        return new CustomerMapper(CustomerEntity.class);
    }

    @Bean
    public OrderMapper orderMapper() {
        return new OrderMapper(OrderEntity.class);
    }

    @Bean
    public OrderProductMapper orderProductMapper() {
        return new OrderProductMapper(OrderProductEntity.class);
    }

    @Bean
    public EmployeeMapper employeeMapper() {
        return new EmployeeMapper(EmployeeEntity.class);
    }

    @Bean
    public OrganizationUnitMapper organizationUnitMapper() {
        return new OrganizationUnitMapper(OrganizationUnitEntity.class);
    }

}
