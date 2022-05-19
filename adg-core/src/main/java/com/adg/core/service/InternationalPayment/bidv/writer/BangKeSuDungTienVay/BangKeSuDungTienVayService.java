package com.adg.core.service.InternationalPayment.bidv.writer.BangKeSuDungTienVay;

import com.adg.core.OfficeHandler.excel.ExcelTable;
import com.adg.core.OfficeHandler.excel.ExcelUtils;
import com.adg.core.OfficeHandler.excel.ExcelWriter;
import com.adg.core.service.FileGenerator.AdgExcelTableHeaderMetadata;
import com.merlin.asset.core.utils.DateTimeUtils;
import com.merlin.asset.core.utils.MapUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.30 13:28
 */
public class BangKeSuDungTienVayService {

    private ExcelWriter excelWriter;
    private ExcelTable excelTable;
    private Map<String, Object> data;
    private String outputFolder;

    private final static String template = "./adg-server/src/main/resources/bidv/BẢNG KÊ SỬ DỤNG TIỀN VAY.xlsx";
//    private final static String OUTPUT_FILE = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/output";

    public BangKeSuDungTienVayService(String outputFolder, List<Map<String, Object>> hoaDonRecords) {
        this.outputFolder = outputFolder;
        this.excelWriter = new ExcelWriter(template);
        this.excelWriter.openSheet();
        this.excelTable = new ExcelTable(this.excelWriter, AdgExcelTableHeaderMetadata.getBangKeSuDungTienVay());
        this.data = this.transformHoaDonRecords(hoaDonRecords);
    }

    public Map<String, Object> transformHoaDonRecords(List<Map<String, Object>> hoaDonRecords) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> bangKe = new ArrayList<>();

        for (Map<String, Object> hoaDonRecord : hoaDonRecords) {
            Map<String, Object> transformedRecord = new HashMap<>();
            for (BangKeSuDungTienVayHeaderInfoMetadata headerInfoMetadata : BangKeSuDungTienVayHeaderInfoMetadata.values()) {
                transformedRecord.put(headerInfoMetadata.getHeaderName(), headerInfoMetadata.transformCallback.apply(hoaDonRecord));
            }
            bangKe.add(transformedRecord);
        }
        result.put("Bảng kê", bangKe);
        return result;
    }

    public void insertRecordToTable() {
        List<Map<String, Object>> records = MapUtils.getListMapStringObject(this.data, "Bảng kê");
        records.forEach(item -> this.excelTable.insert(item));
    }

    public void exportDocument() {
        this.insertRecordToTable();
        this.excelTable.removeSampleRow();
        this.fillSum();
        this.fillDescription();
        this.fillAdditionalDescription();
        this.build();
    }

    private void build() {
        String fileName = String.format("Bảng kê sử dụng tiền vay - %s.xlsx", DateTimeUtils.convertZonedDateTimeToFormat(ZonedDateTime.now(), "Asia/Ho_Chi_Minh", DateTimeUtils.MA_DATE_TIME_FORMATTER));
        this.excelWriter.build(outputFolder + "/" + fileName);
    }

    private void fillSum() {
        Cell soTienHeaderCell = this.excelWriter.getCell(BangKeSuDungTienVayHeaderInfoMetadata.SoTien.getCellAddress());
        String startCell = this.excelWriter.getCell(
                this.excelWriter.getRow(soTienHeaderCell.getRowIndex() + 1),
                soTienHeaderCell.getColumnIndex()
        ).getAddress().formatAsString();

        String endCell = this.excelWriter.getCell(
                this.excelWriter.getRow(soTienHeaderCell.getRowIndex() + this.excelTable.getSize()),
                soTienHeaderCell.getColumnIndex()
        ).getAddress().formatAsString();

        Cell tongCell = this.excelWriter.getCell(
                this.excelWriter.getRow(soTienHeaderCell.getRowIndex() + this.excelTable.getSize() + 1),
                soTienHeaderCell.getColumnIndex()
        );

        ExcelUtils.setCell(tongCell, String.format("SUM(%s:%s)", startCell, endCell), CellType.FORMULA);
    }

    private void fillDescription() {
        String description = String.format(
                "Chi tiết nội dung sử dụng tiền vay theo hợp đồng tín dụng ngắn hạn cụ thể số : 01.219/2021/8088928/HĐTD ngày %s được ký kết giữa Ngân hàng và Bên vay.",
                DateTimeUtils.convertZonedDateTimeToFormat(ZonedDateTime.now(), "Asia/Ho_Chi_Minh", DateTimeUtils.getFormatterWithDefaultValue(DateTimeUtils.FMT_09))
            );
        String originalCellAddress = "A7";
        Cell originalCell = this.excelWriter.getCell(originalCellAddress);
        originalCell.setCellValue(description);
    }

    private void fillAdditionalDescription() {
        String description = String.format(
                "Bảng kê này là một bộ phận trong thể tách rời hợp đồng tín dụng ngắn hạn cụ thể số 01.219/2021/8088928/HĐTD ngày %s được ký kết giữa Ngân hàng và Bên vay.",
                DateTimeUtils.convertZonedDateTimeToFormat(ZonedDateTime.now(), "Asia/Ho_Chi_Minh", DateTimeUtils.getFormatterWithDefaultValue(DateTimeUtils.FMT_09))
        );

        String originalCellAddress = "A13";

        this.excelWriter.setShiftCellValue(originalCellAddress, this.excelTable.getSize(), description, CellType.STRING);
    }

    public static void main(String[] args) {
        Map<String, Object> item = MapUtils.ImmutableMap()
                .put("TT", 1)
                .put("Nội dung", "Thanh toán tiền hàng theo hóa đơn  85 hết.")
                .put("Số hiệu chứng từ kế toán", "UNC")
                .put("Số tiền (VNĐ)",  3217500000L)
                .put("Số hợp đồng", "")
                .put("Tên đơn vị, số tài khoản ngân hàng người thụ hưởng", "CÔNG TY TNHH THƯƠNG MẠI SẢN XUẤT NHỰA NHẬT PHONG \n" +
                        "SỐ TK: 10511 0077 2008\n" +
                        " TẠI NH: MB-CN TÂN CẢNG, TP HCM")
                .build();

        Map<String, Object> item2 = MapUtils.ImmutableMap()
                .put("TT", 1)
                .put("Nội dung", "Thanh toán tiền hàng theo hóa đơn  85 hết.")
                .put("Số hiệu chứng từ kế toán", "UNC")
                .put("Số tiền (VNĐ)",  3217500000L)
                .put("Số hợp đồng", "")
                .put("Tên đơn vị, số tài khoản ngân hàng người thụ hưởng", "CÔNG TY TNHH THƯƠNG MẠI SẢN XUẤT NHỰA NHẬT PHONG \n" +
                        "SỐ TK: 10511 0077 2008\n" +
                        " TẠI NH: MB-CN TÂN CẢNG, TP HCM")
                .build();

        Map<String, Object> item3 = MapUtils.ImmutableMap()
                .put("TT", 1)
                .put("Nội dung", "Thanh toán tiền hàng theo hóa đơn  85 hết.")
                .put("Số hiệu chứng từ kế toán", "UNC")
                .put("Số tiền (VNĐ)",  3217500000L)
                .put("Số hợp đồng", "")
                .put("Tên đơn vị, số tài khoản ngân hàng người thụ hưởng", "CÔNG TY TNHH THƯƠNG MẠI SẢN XUẤT NHỰA NHẬT PHONG \n" +
                        "SỐ TK: 10511 0077 2008\n" +
                        " TẠI NH: MB-CN TÂN CẢNG, TP HCM")
                .build();

//        BangKeSuDungTienVayService bangKeSuDungTienVayService = new BangKeSuDungTienVayService();
//        bangKeSuDungTienVayService.insertRecordToTable(Arrays.asList(item, item2, item3));
//        bangKeSuDungTienVayService.excelTable.removeSampleRow();
//        bangKeSuDungTienVayService.fillSum();
//        bangKeSuDungTienVayService.fillDescription();
//        bangKeSuDungTienVayService.fillAdditionalDescription();
//        bangKeSuDungTienVayService.build();

    }
}
