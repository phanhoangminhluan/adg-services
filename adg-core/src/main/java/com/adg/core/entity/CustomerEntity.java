package com.adg.core.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.12 15:25
 */
@Data
@Entity
@Table(name = "Customers")
public class CustomerEntity implements Serializable {

    @Id
    private String asyncId;

    @Column
    private int id;

    @Column
    private String accountName;

    @Column
    private String accountNumber;

    @Column
    private String accountShortName;

    @Column
    private String bankAccount;

    @Column
    private String bankName;

    @Column
    private String billingAddress;

    @Column
    private String billingCountry;

    @Column
    private String billingProvince;

    @Column
    private String budgetCode;

    @Column
    private String createdBy;

    @Column
    private ZonedDateTime createdDate;

    @Column
    private String description;

    @Column
    private String fax;

    @Column
    private int formLayoutId;

    @Column
    private boolean isPublic;

    @Column
    private String modifiedBy;

    @Column
    private ZonedDateTime modifiedDate;

    @Column
    private String officeEmail;

    @Column
    private String officeTel;

    @Column
    private int organizationUnitId;

    @Column
    private String organizationUnitName;

    @Column
    private int ownerId;

    @Column
    private String ownerName;

    @Column
    private String parentAccountName;

    @Column
    private String shippingAddress;

    @Column
    private String shippingCountry;

    @Column
    private String taxCode;

    @Column
    private String website;

}