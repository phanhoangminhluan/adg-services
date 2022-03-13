package com.adg.core.dto;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 15:47
 */
@Data
public class CustomerDTO {

    private int id;
    private int formLayoutId;
    private int ownerId;
    private String ownerName;
    private String accountName;
    private String accountShortName;
    private String officeTel;
    private String officeEmail;
    private String fax;
    private String website;
    private String parentAccountName;
    private String accountNumber;
    private String taxCode;
    private String budgetCode;
    private String bankAccount;
    private String bankName;
    private String description;
    private String billingAddress;
    private String billingCountry;
    private String billingProvince;
    private String shippingAddress;
    private String shippingCountry;
    private String isPublic;
    private ZonedDateTime modifiedDate;
    private String modifiedBy;
    private ZonedDateTime createdDate; //
    private String createdBy;
    private String asyncId;
    private int organizationUnitId;
    private String organizationUnitName;

}
