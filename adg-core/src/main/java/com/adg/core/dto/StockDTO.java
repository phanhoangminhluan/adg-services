package com.adg.core.dto;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.08 16:47
 */
@Data
public class StockDTO {

    private String asyncId;
    private int id;
    private String actDatabaseId;
    private String createdBy;
    private ZonedDateTime createdDate;
    private String description;
    private boolean inactive;
    private String modifiedBy;
    private ZonedDateTime modifiedDate;
    private String stockCode;
    private String stockName;

}
