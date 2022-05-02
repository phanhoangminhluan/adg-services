package com.adg.core.OfficeHandler.word;

import com.adg.core.service.FileGenerator.AdgWordTableHeaderInfo;
import com.adg.core.service.FileGenerator.bill.bidv.HopDongTinDung.HopDongTinDungTableHeaderInfoMetadata;
import com.merlin.asset.core.utils.DateTimeUtils;
import com.merlin.asset.core.utils.MapUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.02 13:37
 */
public class WordHandler {

    public static void main(String[] args) throws IOException {


        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get("/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/HỢP ĐÒNG TÍN DỤNG.docx")))) {

            Map<Integer, AdgWordTableHeaderInfo> headerMap = new HashMap<>();
            for (HopDongTinDungTableHeaderInfoMetadata value : HopDongTinDungTableHeaderInfoMetadata.values()) {
                headerMap.put(value.getOrdinal(), value);
            }

            WordTable wordTable = new WordTable(doc, headerMap);

            Map<String, Object> data = MapUtils.ImmutableMap()
                    .put("STT", Collections.singletonList("1"))
                    .put("Nội dung", Collections.singletonList("Thanh toán tiền hàng theo hóa đơn  85 hết."))
                    .put("Số hiệu chứng từ kế toán", Collections.singletonList("UNC"))
                    .put("Số tiền (VND)", Collections.singletonList("3,217,500,000"))
                    .put("Tên đơn vị, số tài khoản, Ngân hàng người thụ hưởng", Arrays.asList("CÔNG TY TNHH THƯƠNG MẠI SẢN XUẤT NHỰA NHẬT PHONG", "SỐ TK: 10511 0077 2008", "TẠI NH: MB-CN TÂN CẢNG, TP HCM"))
                    .build();

            wordTable.insertRecord(data);

//            XWPFTableRow row = wordTable.getTable().createRow();
//            XWPFTableCell cell1  = row.getCell(0);
//            CTTcPr ctTcPr1 = cell1.getCTTc().addNewTcPr();
//            CTDecimalNumber ctDecimalNumber = ctTcPr1.addNewGridSpan();
//            ctDecimalNumber.setVal(new BigInteger(String.valueOf(3)));
//            row.removeCell(1);
//            row.removeCell(1);
//            XWPFRun run = WordUtils.Table.setCellWithCenterAlignment(cell1, "Tổng");
//            run.setBold(true);
//            WordUtils.Table.setCellWithCenterAlignment(row.getCell(1), "3,217,500,000");
//            WordUtils.Table.setCellWithCenterAlignment(row.getCell(2), "");

            XWPFTableRow row = wordTable.getTable().createRow();
            XWPFTableCell cell = WordUtils.Table.mergeCell(row, 0, 3);
            XWPFRun run = WordUtils.Table.setCell(cell, "Tổng");
            run.setBold(true);
            WordUtils.Table.makeCenter(cell);

            WordUtils.Table.setCell(row.getCell(1), "3,217,500,000");
            WordUtils.Table.makeCenter(row.getCell(1));

            WordUtils.Table.setCell(row.getCell(2), "");

            doc.write(new FileOutputStream(String.format("/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/output/HỢP ĐỒNG TÍN DỤNG - %s.docx",
                    DateTimeUtils.convertZonedDateTimeToFormat(ZonedDateTime.now(), "Asia/Ho_Chi_Minh", DateTimeUtils.getFormatterWithDefaultValue(DateTimeUtils.FMT_01)))));

        }

    }

}
