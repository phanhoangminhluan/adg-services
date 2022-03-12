package com.adg.core.dto;

import lombok.Data;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.12 15:22
 */
@Data
class SaleOrderProductMappingDTO {

    private int productId;
    private String unit;
    private double price;
    private double amount;
    private double toCurrency;
    private double discount;
    private double tax;
    private double total;
    private String productName;
    private String productCode;
    private String createdDate;
    private String modifiedDate;
    private String asyncId;
    private String taxPercent;
    private double discountPercent;
    private double shippingAmount;
    private double usageUnitAmount;
    private String description;
    private double toCurrencyOc;
    private double discountOc;
    private double taxOc;
    private double totalOc;
    private boolean isDiscountDirectly;
    private double priceAfterTax;
    private int sortOrder;

}
