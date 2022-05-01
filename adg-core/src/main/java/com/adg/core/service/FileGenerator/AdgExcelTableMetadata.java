package com.adg.core.service.FileGenerator;

import com.adg.core.excel.model.accounting.AdgExcelTable;
import com.adg.core.excel.model.accounting.BangKeChungTuDienTuDeNghiGiaiNgan;
import com.adg.core.service.FileGenerator.BangKeSuDungTienVay.BangKeSuDungTienVayTableMetadata;
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
        List<AdgExcelTable> headers = Arrays.asList(BangKeSuDungTienVayTableMetadata.values());

        return AdgExcelTableMetadata.builder()
                .headers(headers)
                .startCellAddress("A10")
                .columnSize(headers.size())
                .build();
    }

}
