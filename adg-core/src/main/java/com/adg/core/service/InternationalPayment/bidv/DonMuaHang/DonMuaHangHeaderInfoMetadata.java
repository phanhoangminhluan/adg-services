package com.adg.core.service.InternationalPayment.bidv.DonMuaHang;

import com.adg.core.service.FileGenerator.AdgExcelTableHeaderInfo;
import org.apache.poi.ss.usermodel.CellType;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.03 12:24
 */
public enum DonMuaHangHeaderInfoMetadata implements AdgExcelTableHeaderInfo {
    MaHang("Mã hàng", "A14", CellType.STRING),
    DienGiai("Diễn giải", "B14", CellType.STRING),
    DonVi("Đơn vị", "C14", CellType.STRING),
    SoLuong("Số lượng", "D14", CellType.NUMERIC),
    DonGia("Đơn giá", "E14", CellType.NUMERIC),
    ThanhTien("Thành tiền", "F14", CellType.NUMERIC),
    ;

    private final String header;
    private final String cellAddress;
    private final CellType cellType;

    DonMuaHangHeaderInfoMetadata(String header, String cellAddress, CellType cellType) {
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
