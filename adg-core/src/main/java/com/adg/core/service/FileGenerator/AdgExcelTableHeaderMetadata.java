package com.adg.core.service.FileGenerator;

import com.adg.core.service.FileGenerator.bill.bidv.BangKeSuDungTienVay.BangKeSuDungTienVayHeaderInfoMetadata;
import com.adg.core.service.FileGenerator.bill.viettin.BangKeChungTuDienTuDeNghiGiaiNgan.BangKeChungTuDienTuDeNghiGiaiNganTableMetadataHeaderInfo;
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
public class AdgExcelTableHeaderMetadata {

    private List<AdgExcelTableHeaderInfo> headers;
    private String startCellAddress;
    private int columnSize;

    public static AdgExcelTableHeaderMetadata getBangKeChungTuDeNghiGiaiNgan() {
        List<AdgExcelTableHeaderInfo> headers = Arrays.asList(BangKeChungTuDienTuDeNghiGiaiNganTableMetadataHeaderInfo.values());

        return AdgExcelTableHeaderMetadata.builder()
                .headers(headers)
                .startCellAddress("A10")
                .columnSize(headers.size())
                .build();
    }

    public static AdgExcelTableHeaderMetadata getBangKeSuDungTienVay() {
        List<AdgExcelTableHeaderInfo> headers = Arrays.asList(BangKeSuDungTienVayHeaderInfoMetadata.values());

        return AdgExcelTableHeaderMetadata.builder()
                .headers(headers)
                .startCellAddress("A10")
                .columnSize(headers.size())
                .build();
    }

}
