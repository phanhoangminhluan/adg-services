package com.adg.core.excel.model.accounting;

import com.adg.core.excel.model.accounting.bill.bidv.BangKeSuDungTienVay;
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
                .headers(headers)
                .startCellAddress("A10")
                .columnSize(headers.size())
                .build();
    }

    public static AdgExcelTableMetadata getBangKeSuDungTienVay() {
        List<AdgExcelTable> headers = Arrays.asList(BangKeSuDungTienVay.values());

        return AdgExcelTableMetadata.builder()
                .headers(headers)
                .startCellAddress("A10")
                .columnSize(headers.size())
                .build();
    }

}
