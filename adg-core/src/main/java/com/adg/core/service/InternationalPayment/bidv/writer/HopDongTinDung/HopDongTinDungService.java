package com.adg.core.service.InternationalPayment.bidv.writer.HopDongTinDung;

import com.adg.core.OfficeHandler.word.WordUtils;
import com.adg.core.OfficeHandler.word.WordWriter;
import com.adg.core.service.FileGenerator.AdgWordTableHeaderMetadata;
import com.adg.core.service.InternationalPayment.bidv.enums.HoaDonHeaderMetadata;
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
 * Created on: 2022.05.02 23:45
 */
public class HopDongTinDungService {

    private final WordWriter wordWriter;
    private final String outputFolder;
    private final Map<String, Object> data;

    private final static String template = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/HỢP ĐÒNG TÍN DỤNG.docx";

    public HopDongTinDungService(String outputFolder, Map<String, Object> hoaDonRecords) {
        this.wordWriter = new WordWriter(template, AdgWordTableHeaderMetadata.getHeaderMapHopDongTinDung());
        this.outputFolder = outputFolder;
        this.data = this.transformHoaDonRecords(hoaDonRecords);
    }

    public Map<String, Object> transformHoaDonRecords(Map<String, Object> hoaDonRecords) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> arr = new ArrayList<>();
        double tongTienVay = 0;
        for (String nhaCungCap : hoaDonRecords.keySet()) {
            Map<String, Object> hoaDonByNcc = MapUtils.getMapStringObject(hoaDonRecords, nhaCungCap);
            Map<String, Object> transformedRecord = new HashMap<>();
            for (HopDongTinDungTableHeaderInfoMetadata headerInfoMetadata : HopDongTinDungTableHeaderInfoMetadata.values()) {
                transformedRecord.put(headerInfoMetadata.getHeaderName(), headerInfoMetadata.transformCallback.apply(hoaDonByNcc));
                switch (headerInfoMetadata) {
                    case SoTienVND: {
                        tongTienVay += ParserUtils.toDouble(MapUtils.getString(hoaDonByNcc, HoaDonHeaderMetadata.TongTienThanhToanCacHoaDon.deAccentedName));
                    }
                }
            }
            arr.add(transformedRecord);
        }


        result.put("Số hợp đồng", "01.21/2021/8088928/HĐTD");
        result.put("Tổng tiền vay", NumberUtils.formatNumber1(tongTienVay));
        result.put("Ngày ký hợp đồng tín dụng", DateTimeUtils.convertZonedDateTimeToFormat(ZonedDateTime.now(), "Asia/Ho_Chi_Minh", DateTimeUtils.getFormatterWithDefaultValue("dd-MM-yyyy")));
        result.put("Ngày ký", String.format("TPHCM, ngày %s tháng %s năm %s", ZonedDateTime.now().getDayOfMonth(), ZonedDateTime.now().getMonthValue(), ZonedDateTime.now().getYear()));
        result.put("Tổng tiền vay bằng chữ", "Hello");
        result.put("Nội dung thanh toán", arr);

        return result;
    }
    public void exportDocument() {
        this.fillTextData();
        this.fillTableData();
        this.fillTableSumData();
        this.build();
    }

    private void fillTextData() {
        this.wordWriter.fillTextData(data);
    }
    private void fillTableData() {
        this.wordWriter.fillTableData(MapUtils.getListMapStringObject(data, "Nội dung thanh toán"));

    }

    private void fillTableSumData() {
        XWPFTableRow row = this.wordWriter.getWordTable().getTable().createRow();

        XWPFTableCell sumCell = WordUtils.Table.mergeCell(row, 0, 3);
        sumCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        XWPFRun run = WordUtils.Table.setCell(sumCell, "Tổng");
        run.setBold(true);
        run.setFontSize(11);
        WordUtils.Table.makeCenter(sumCell);

        XWPFTableCell calculatedSell = row.getCell(1);
        calculatedSell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        WordUtils.Table.setCell(calculatedSell, MapUtils.getString(data, "Tổng tiền vay")).setFontSize(11);
        WordUtils.Table.makeCenter(calculatedSell);

    }

    private void build() {
        String fileName = String.format("Hợp đồng tín dung - %s.docx",
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

        String sampleDataFile = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/SampleData/HỢP ĐỒNG TÍN DỤNG.json";
        String json = FileUtils.readContent(sampleDataFile);
        Map<String, Object> data = JsonUtils.fromJson(json, JsonUtils.TYPE_TOKEN.MAP_STRING_OBJECT.type);

        HopDongTinDungService hopDongTinDungService = new HopDongTinDungService(outputFolder, data);
        hopDongTinDungService.exportDocument();
    }

}
