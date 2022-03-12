package com.adg.core.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 15:51
 */
@Data
public class OrderDTO {

    private int formLayoutId;
    private String formLayoutIdText;
    private int ownerId;
    private String ownerIdText;
    private String saleOrderNo;
    private String saleOrderName;
    private String saleOrderDate;
    private String bookDate;
    private int contractParentId;
    private int accountId;
    private String accountName;
    private String contactName;
    private double saleOrderAmount;
    private String deadlineDate;
    private int revenueStatusId;
    private String revenueStatusIdText;
    private double recordedSale;
    private String status;
    private String description;
    private boolean isUseCurrency;
    private String currencyType;
    private double exchangeRate;
    private double totalReceiptedAmount;
    private double balanceReceiptAmount;
    private boolean isInvoiced;
    private double invoicedAmount;
    private double unInvoicedAmount;
    private boolean unSubcrible;
    private String billingCountry;
    private String billingProvince;
    private String billingAddress;
    private String shippingCountry;
    private String shippingAddress;
    private int organizationUnitId;
    private String organizationUnitName;
    private String createdBy;
    private String modifiedBy;
    private String createdDate;
    private String modifiedDate;
    private boolean isPublic;
    private String asyncId;
    private boolean isDeleted;
    private double totalSummary;
    private double taxSummary;
    private double discountSummary;
    private double toCurrencySummary;
    private boolean isSentBill;
    private String deliveryStatus;
    private String payStatus;
    private double shippingAmountSummary;
    private boolean isContractPartner;
    private double amountSummary;
    private String recordedSaleUsersId;
    private String recordedSaleUsersName;
    private String recordedSaleOrganizationUnitId;
    private String recordedSaleOrganizationUnitName;
    private boolean isParentSaleOrder;
    private double toCurrencySummaryOc;
    private double discountSummaryOc;
    private double taxSummaryOc;
    private double totalSummaryOc;
    private double saleOrderAmountOc;
    private double totalReceiptedAmountOc;
    private double balanceReceiptAmountOc;
    private double invoicedAmountOc;
    private String opportunityId;
    private String quoteId;
    private String shippingContactId;
    private List<SaleOrderProductMappingDTO> saleOrderProductMappingDTOs = null;

}

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