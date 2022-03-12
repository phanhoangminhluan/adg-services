package com.adg.core.dto;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 15:56
 */
@Data
public class ProductDTO {

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
    private String modifiedBy;
    private ZonedDateTime modifiedDate;
    private int organizationUnitId;
    private int ownerId;
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
