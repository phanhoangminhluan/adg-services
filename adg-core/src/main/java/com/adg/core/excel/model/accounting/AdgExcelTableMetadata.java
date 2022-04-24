package com.adg.core.excel.model.accounting;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.24 14:18
 */
@Data
@Builder
public class AdgExcelTableMetadata {

    private List<AdgExcelTable> headers;
    private String startCellAddress;
    private int columnSize;

    public static AdgExcelTableMetadata getBangKeChungTuDeNghiGiaiNgan() {
        List<AdgExcelTable> headers = Arrays.asList(BangKeChungTuDienTuDeNghiGiaiNgan.values());

        return AdgExcelTableMetadata.builder()
                .headers(Arrays.asList(BangKeChungTuDienTuDeNghiGiaiNgan.values()))
                .startCellAddress("A10")
                .columnSize(headers.size())
                .build();
    }

}
