package com.adg.core.entity;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.12 15:38
 */
@Data
public class ProductEntity {

    private String asyncId;
    private int id;
    private double conversionRate;
    private String createdBy;
    private ZonedDateTime createdDate;
    private String description;
    private int formLayoutId;
    private boolean inactive;
    private boolean isDeleted;
    private boolean isFollowSerialNumber;
    private boolean isPublic;
    private boolean isUseTax;
    private ZonedDateTime modifiedDate;
    private String modifiedBy;
    private int ownerId;
    private int organizationUnitId;
    private String ownerName;
    private boolean priceAfterTax;
    private String productCategoryName;
    private String productCode;
    private String productName;
    private double purchasedPrice;
    private double quantityDemanded;
    private double quantityInstock;
    private double quantityOrdered;
    private boolean taxable;
    private String taxIdText;
    private double unitCost;
    private double unitPrice;
    private double unitPrice1;
    private double unitPrice2;
    private double unitPriceFixed;
    private String usageUnitIdText;
    private int vendorNameId;


}
