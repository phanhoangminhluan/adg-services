package com.adg.core.service.InternationalPayment.bidv.enums;

import com.merlin.asset.core.utils.StringUtils;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.10 22:46
 */
public enum HoaDonHeaderMetadata {
    NgayHachToan("Ngày hạch toán", false, ExcelHeaderType.DATE),
    SoChungTu("Số chứng từ", false, ExcelHeaderType.STRING),
    NgayChungTu("Ngày chứng từ", false, ExcelHeaderType.DATE),
    SoHoaDon("Số hoá đơn", false, ExcelHeaderType.STRING),
    NhaCungCap("Nhà cung cấp", false, ExcelHeaderType.STRING),
    DienGiai("Diễn giải", false, ExcelHeaderType.STRING),
    TongTienHang("Tổng tiền hàng", false, ExcelHeaderType.DOUBLE),
    TienChietKhau("Tiền chiết khấu", false, ExcelHeaderType.DOUBLE),
    TienThueGTGT("Tiền thuế GTGT", false, ExcelHeaderType.DOUBLE),
    TongTienThanhTOan("Tổng tiền thanh toán", false, ExcelHeaderType.DOUBLE),
    ChiPhiMuaHang("Chi phí mua hàng", false, ExcelHeaderType.DOUBLE),
    GiaTriNhapKho("Giá trị nhập kho", false, ExcelHeaderType.DOUBLE),
    NhanHoaDon("Nhận hoá đơn", false, ExcelHeaderType.STRING),
    LaChiPhiMuaHang("Là chi phí mua hàng", false, ExcelHeaderType.BOOLEAN),
    LoaiChungTu("Loại chứng từ", false, ExcelHeaderType.STRING),
    PhiTruocHaiQuan("Phí trước hải quan", false, ExcelHeaderType.DOUBLE),
    TienThueNK("Tiền thuế NK", false, ExcelHeaderType.DOUBLE),
    TienThueTTDB("Tiền thuế TTĐB", false, ExcelHeaderType.DOUBLE),
    SoChungTuSoQT("Số chứng từ (Sổ QT)", true, ExcelHeaderType.DOUBLE),
    ;
    public final String name;
    public final String deAccentedName;
    public final boolean isNullable;
    public final ExcelHeaderType type;

    HoaDonHeaderMetadata(String name, boolean isNullable, ExcelHeaderType type) {
        this.name = name;
        this.deAccentedName = StringUtils.deAccent(this.name);
        this.isNullable = isNullable;
        this.type = type;
    }
}
