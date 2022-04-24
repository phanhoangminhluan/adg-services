package com.adg.core.excel.model.accounting;

import org.apache.poi.ss.usermodel.CellType;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.24 11:21
 */
public interface AdgExcelTable {

    String getHeaderName();
    String getCellAddress();
    int getOrdinal();
    CellType getCellType();
}
