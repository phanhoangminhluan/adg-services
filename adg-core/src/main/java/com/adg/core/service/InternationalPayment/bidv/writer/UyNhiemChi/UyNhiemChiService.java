package com.adg.core.service.InternationalPayment.bidv.writer.UyNhiemChi;

import com.adg.core.OfficeHandler.word.WordWriter;
import com.adg.core.service.InternationalPayment.bidv.NhaCungCapDTO;
import com.adg.core.service.InternationalPayment.bidv.enums.HoaDonHeaderMetadata;
import com.adg.core.utils.MoneyUtils;
import com.merlin.asset.core.utils.*;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.03 15:33
 */
public class UyNhiemChiService {

    private final WordWriter wordWriter;
    private final String outputFolder;
    private final Map<String, Object> data;

    private final static String template = "./adg-server/src/main/resources/bidv/UỶ NHIỆM CHI.docx";

    public UyNhiemChiService(String outputFolder, Map<String, Object> data) {
        this.wordWriter = new WordWriter(template, new HashMap<>());
        this.outputFolder = outputFolder;
        this.data = this.transformHoaDonRecords(data);
    }

    public Map<String, Object> transformHoaDonRecords(Map<String, Object> hoaDonRecords) {
        Map<String, Object> result = new HashMap<>();

        NhaCungCapDTO nhaCungCapDTO = NhaCungCapDTO.nhaCungCapMap.get(MapUtils.getString(hoaDonRecords, HoaDonHeaderMetadata.NhaCungCap.deAccentedName));
        result.put("Người cung cấp", MapUtils.getString(hoaDonRecords, HoaDonHeaderMetadata.NhaCungCap.deAccentedName));
        result.put("Số tiền bằng số", NumberUtils.formatNumber1(MapUtils.getDouble(hoaDonRecords, HoaDonHeaderMetadata.TongTienThanhToanCacHoaDon.deAccentedName)));
        result.put("Số tài khoản", nhaCungCapDTO == null ?  "" : nhaCungCapDTO.getSoTaiKhoan());
        result.put("Ngân hàng", nhaCungCapDTO == null ?  "" : nhaCungCapDTO.getTenNganHang());
        result.put("Số tiền bằng chữ", MoneyUtils.convertMoneyToText(MapUtils.getDouble(hoaDonRecords, HoaDonHeaderMetadata.TongTienThanhToanCacHoaDon.deAccentedName)));
        result.put("Ngày", DateTimeUtils.convertZonedDateTimeToFormat(ZonedDateTime.now(), "Asia/Ho_Chi_Minh", DateTimeUtils.getFormatterWithDefaultValue("dd/MM/yyyy")));

        return result;
    }

    public void exportDocument() {
        this.wordWriter.fillTextData(data);
        this.build();
    }

    private void build() {
        String fileName = String.format("Uỷ nhiệm chi - %s - %s.docx",
                MapUtils.getString(this.data, "Người cung cấp"),
                DateTimeUtils.convertZonedDateTimeToFormat(
                        ZonedDateTime.now(),
                        "Asia/Ho_Chi_Minh",
                        DateTimeUtils.getFormatterWithDefaultValue(DateTimeUtils.FMT_01)
                )
        );
        this.wordWriter.build(outputFolder + "/" + fileName);
    }

    public static void main(String[] args) {

        Map<String, Object> data = JsonUtils.fromJson(
                FileUtils.readContent("/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/SampleData/UỶ NHIỆM CHI.json"),
                JsonUtils.TYPE_TOKEN.MAP_STRING_OBJECT.type
        );

        UyNhiemChiService uyNhiemChiService = new UyNhiemChiService("/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/output", data);
        uyNhiemChiService.exportDocument();
    }

}
