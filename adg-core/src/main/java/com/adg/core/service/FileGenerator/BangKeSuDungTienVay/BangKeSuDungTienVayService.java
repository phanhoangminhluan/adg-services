package com.adg.core.service.FileGenerator.BangKeSuDungTienVay;

import com.adg.core.excel.ExcelUtils;
import com.adg.core.excel.ExcelWriter;
import com.adg.core.excel.model.ExcelTable;
import com.adg.core.service.FileGenerator.AdgExcelTableMetadata;
import com.merlin.asset.core.utils.DateTimeUtils;
import com.merlin.asset.core.utils.MapUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.30 13:28
 */
public class BangKeSuDungTienVayService {

    private ExcelWriter excelWriter;
    private ExcelTable excelTable;

    private final static String INPUT_FILE = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/BẢNG KÊ SỬ DỤNG TIỀN VAY.xlsx";
    private final static String OUTPUT_FILE = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/output";

    public BangKeSuDungTienVayService() {
        this.excelWriter = new ExcelWriter(INPUT_FILE);
        this.excelWriter.openSheet();
        this.excelTable = new ExcelTable(this.excelWriter, AdgExcelTableMetadata.getBangKeSuDungTienVay());
    }

    public void insertRecordToTable(List<Map<String, Object>> items) {
        items.forEach(item -> this.excelTable.insert(item));
    }

    public void build() {
        String fileName = String.format("BẢNG KÊ SỬ DỤNG TIỀN VAY - %s.xlsx", DateTimeUtils.convertZonedDateTimeToFormat(ZonedDateTime.now(), "Asia/Ho_Chi_Minh", DateTimeUtils.MA_DATE_TIME_FORMATTER));
        this.excelWriter.build(OUTPUT_FILE + "/" + fileName);
    }

    public void fillSum() {
        Cell soTienHeaderCell = this.excelWriter.getCell(BangKeSuDungTienVayTableMetadata.SoTien.getCellAddress());
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

    public void fillDescription() {
        String description = String.format(
                "Chi tiết nội dung sử dụng tiền vay theo hợp đồng tín dụng ngắn hạn cụ thể số : 01.219/2021/8088928/HĐTD ngày %s được ký kết giữa Ngân hàng và Bên vay.",
                DateTimeUtils.convertZonedDateTimeToFormat(ZonedDateTime.now(), "Asia/Ho_Chi_Minh", DateTimeUtils.getFormatterWithDefaultValue(DateTimeUtils.FMT_03))
            );
        String originalCellAddress = "A7";
        Cell originalCell = this.excelWriter.getCell(originalCellAddress);
        originalCell.setCellValue(description);
    }

    public void fillAdditionalDescription() {
        String description = String.format(
                "Bảng kê này là một bộ phận trong thể tách rời hợp đồng tín dụng ngắn hạn cụ thể số 01.219/2021/8088928/HĐTD ngày %s được ký kết giữa Ngân hàng và Bên vay.",
                DateTimeUtils.convertZonedDateTimeToFormat(ZonedDateTime.now(), "Asia/Ho_Chi_Minh", DateTimeUtils.getFormatterWithDefaultValue(DateTimeUtils.FMT_03))
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

        BangKeSuDungTienVayService bangKeSuDungTienVayService = new BangKeSuDungTienVayService();
        bangKeSuDungTienVayService.insertRecordToTable(Arrays.asList(item, item2, item3));
        bangKeSuDungTienVayService.excelTable.removeSampleRow();
        bangKeSuDungTienVayService.fillSum();
        bangKeSuDungTienVayService.fillDescription();
        bangKeSuDungTienVayService.fillAdditionalDescription();
        bangKeSuDungTienVayService.build();

    }
}
