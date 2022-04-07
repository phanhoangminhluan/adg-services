package com.adg.core.common.constants;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 23:52
 */
public class PubSubConstants {

    public static class Stock extends PubSubInfo {
        public static final String TOPIC_NAME = "stock";
        public static final String GROUP_ID = "stock_sync_to_postgresql";
        public static final String CONSUMER_FACTORY = "stockConsumerFactory";
        public static final String LISTENER_CONTAINER_FACTORY = "stockConcurrentKafkaListenerContainerFactory";
    }


    public static class Product extends PubSubInfo {
        public static final String TOPIC_NAME = "product";
        public static final String GROUP_ID = "product_sync_to_postgresql";
        public static final String CONSUMER_FACTORY = "productConsumerFactory";
        public static final String LISTENER_CONTAINER_FACTORY = "productConcurrentKafkaListenerContainerFactory";
    }


    public static class Order extends PubSubInfo {
        public static final String TOPIC_NAME = "order";
        public static final String GROUP_ID = "order_sync_to_postgresql";
        public static final String CONSUMER_FACTORY = "orderConsumerFactory";
        public static final String LISTENER_CONTAINER_FACTORY = "orderConcurrentKafkaListenerContainerFactory";
    }

    public static class Customer extends PubSubInfo {
        public static final String TOPIC_NAME = "customer";
        public static final String GROUP_ID = "customer_sync_to_postgresql";
        public static final String CONSUMER_FACTORY = "customerConsumerFactory";
        public static final String LISTENER_CONTAINER_FACTORY = "customerConcurrentKafkaListenerContainerFactory";
    }

    public static class Employee extends PubSubInfo {
        public static final String TOPIC_NAME = "employee";
        public static final String GROUP_ID = "employee_sync_to_postgresql";
        public static final String CONSUMER_FACTORY = "employeeConsumerFactory";
        public static final String LISTENER_CONTAINER_FACTORY = "employeeConcurrentKafkaListenerContainerFactory";
    }

    public static class OrganizationUnit extends PubSubInfo {
        public static final String TOPIC_NAME = "organization-unit";
        public static final String GROUP_ID = "orgUnit_sync_to_postgresql";
        public static final String CONSUMER_FACTORY = "orgUnitConsumerFactory";
        public static final String LISTENER_CONTAINER_FACTORY = "orgUnitConcurrentKafkaListenerContainerFactory";
    }

}

