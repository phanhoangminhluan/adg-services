package com.adg.core.service.InternationalPayment.bidv.BangKeSuDungTienVay;

import com.adg.core.service.FileGenerator.AdgExcelTableHeaderInfo;
import org.apache.poi.ss.usermodel.CellType;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.30 13:43
 */
public enum BangKeSuDungTienVayHeaderInfoMetadata implements AdgExcelTableHeaderInfo {
    TT("TT", "A9", CellType.NUMERIC),
    NoiDung("Nội dung", "B9", CellType.STRING),
    SoHieuChungTuKeToan("Số hiệu chứng từ kế toán", "C9", CellType.STRING),
    SoTien("Số tiền (VNĐ)", "D9", CellType.NUMERIC),
    SoHopDong("Số hợp đồng", "E9", CellType.STRING),
    TenDonViVaSoTK("Tên đơn vị, số tài khoản ngân hàng người thụ hưởng", "F9", CellType.STRING);

    private final String header;
    private final String cellAddress;
    private final CellType cellType;

    BangKeSuDungTienVayHeaderInfoMetadata(String header, String cellAddress, CellType cellType) {
        this.header = header;
        this.cellAddress = cellAddress;
        this.cellType = cellType;
    }

    @Override
    public String getHeaderName() {
        return this.header;
    }

    @Override
    public String getCellAddress() {
        return this.cellAddress;
    }

    @Override
    public int getOrdinal() {
        return this.ordinal();
    }

    @Override
    public CellType getCellType() {
        return this.cellType;
    }
}
