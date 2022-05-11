package com.adg.core.service.InternationalPayment.bidv.writer.BienBanKiemTraSuDungVonVay;

import com.adg.core.OfficeHandler.word.WordUtils;
import com.adg.core.OfficeHandler.word.WordWriter;
import com.adg.core.service.FileGenerator.AdgWordTableHeaderMetadata;
import com.adg.core.service.InternationalPayment.bidv.enums.HoaDonHeaderMetadata;
import com.adg.core.utils.MoneyUtils;
import com.merlin.asset.core.utils.*;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.09 22:25
 */
public class BienBanKiemTraSuDungVonVayService {

    private final WordWriter wordWriter;
    private final String outputFolder;
    private final Map<String, Object> data;

    private final static String template = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/BIÊN BẢN KIỂM TRA SỬ DỤNG VỐN VAY.docx";

    public BienBanKiemTraSuDungVonVayService(String outputFolder, Map<String, Object> hoaDonRecords) {
        this.wordWriter = new WordWriter(template, AdgWordTableHeaderMetadata.getHeaderBienBanKiemTraSuDungVonVay());
        this.outputFolder = outputFolder;
        this.data = this.transformHoaDonRecords(hoaDonRecords);
    }

    public void exportDocument() {
        this.fillTextData();
        this.fillTableData();
        this.fillTableSumData();
        this.build();
    }

    public Map<String, Object> transformHoaDonRecords(Map<String, Object> hoaDonRecords) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> arr = new ArrayList<>();
        double tongTienVay = 0;
        for (String nhaCungCap : hoaDonRecords.keySet()) {
            Map<String, Object> hoaDonByNcc = MapUtils.getMapStringObject(hoaDonRecords, nhaCungCap);
            Map<String, Object> transformedRecord = new HashMap<>();
            for (BienBanKiemTraSuDungVonVayHeaderInfoMetadata headerInfoMetadata : BienBanKiemTraSuDungVonVayHeaderInfoMetadata.values()) {
                transformedRecord.put(headerInfoMetadata.getHeaderName(), headerInfoMetadata.transformCallback.apply(hoaDonByNcc));
                switch (headerInfoMetadata) {
                    case SoTienVND: {
                        tongTienVay += ParserUtils.toDouble(MapUtils.getString(hoaDonByNcc, HoaDonHeaderMetadata.TongTienThanhToanCacHoaDon.deAccentedName));
                    }
                }
            }
            arr.add(transformedRecord);
        }


        result.put("Ngày hôm nay", String.format("ngày %s tháng %s năm %s", ZonedDateTime.now().getDayOfMonth(), ZonedDateTime.now().getMonthValue(), ZonedDateTime.now().getYear()));
        result.put("Đại diện khách hàng", "");
        result.put("Đại diện ngân hàng", "");
        result.put("Mã số giấy đề nghị giải ngân", "01.219/2021/8088928/HĐTD");
        result.put("Tổng tiền vay bằng số", NumberUtils.formatNumber1(tongTienVay));
        result.put("Số tiền vay", NumberUtils.formatNumber1(tongTienVay));
        result.put("Tổng tiền vay bằng chữ", MoneyUtils.convertMoneyToText(tongTienVay));
        result.put("Danh sách thanh toán tiền hàng", arr);
        result.put("Ngày đề nghị giải ngân", DateTimeUtils.convertZonedDateTimeToFormat(ZonedDateTime.now(), "Asia/Ho_Chi_Minh", DateTimeUtils.getFormatterWithDefaultValue("dd-MM-yyyy")));


        return result;
    }

    private void fillTextData() {
        this.wordWriter.fillTextData(data);
    }
    private void fillTableData() {
        this.wordWriter.fillTableData(MapUtils.getListMapStringObject(data, "Danh sách thanh toán tiền hàng"));

    }

    private void fillTableSumData() {
        XWPFTableRow row = this.wordWriter.getWordTable().getTable().createRow();
        XWPFTableCell sumCell = WordUtils.Table.mergeCell(row, 0, 3);
        sumCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        XWPFRun run = WordUtils.Table.setCell(sumCell, "Tổng");
        run.setBold(true);
        run.setFontSize(11);
        WordUtils.Table.makeCenter(sumCell);

        XWPFTableCell calculatedSell = WordUtils.Table.mergeCell(row, 1, 2);
        calculatedSell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        String val = String.format("%s VND (Bằng chữ: %s.)", MapUtils.getString(data, "Tổng tiền vay bằng số"), MapUtils.getString(data, "Tổng tiền vay bằng chữ"));
        WordUtils.Table.setCell(calculatedSell, val).setFontSize(11);
        WordUtils.Table.makeCenter(calculatedSell);

    }

    private void build() {
        String fileName = String.format("Biên bản kiểm tra sử dụng vốn vay - %s.docx",
                DateTimeUtils.convertZonedDateTimeToFormat(
                        ZonedDateTime.now(),
                        "Asia/Ho_Chi_Minh",
                        DateTimeUtils.getFormatterWithDefaultValue(DateTimeUtils.FMT_01)
                )
        );
        this.wordWriter.build(outputFolder + "/" + fileName);
    }

    public static void main(String[] args) {
        String outputFolder = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/output";

        String sampleDataFile = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/SampleData/BIÊN BẢN KIỂM TRA SỬ DỤNG VỐN VAY.json";
        String json = FileUtils.readContent(sampleDataFile);
        Map<String, Object> data = JsonUtils.fromJson(json, JsonUtils.TYPE_TOKEN.MAP_STRING_OBJECT.type);

        BienBanKiemTraSuDungVonVayService service = new BienBanKiemTraSuDungVonVayService(outputFolder, data);
        service.exportDocument();
    }

}
