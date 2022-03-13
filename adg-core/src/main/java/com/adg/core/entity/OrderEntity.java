package com.adg.core.entity;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.12 15:30
 */
@Data
public class OrderEntity {

    private String id;
    private String saleProductId;
    private String saleOrderId;
    private ZonedDateTime createdDate; //
    private int week;
    private String orderCode;
    private String status; //
    private ZonedDateTime deliveriedDate;
    private String customerCode;
    private String customerName;
    private String staffCode;
    private String staffName;
    private String businessUnit;
    private String productCode;
    private String productName;
    private String unit;
    private double quantity;
    private double estimateQuantityByUnit;
    private double deliveriedQuantity;
    private double remainingQuantity;
    private double actualRevenue;
    private double remainingRevenue;
    private double revenueByTax;
    private double actualRevenueByTax;
    private ZonedDateTime saleOrderDate; //


}
