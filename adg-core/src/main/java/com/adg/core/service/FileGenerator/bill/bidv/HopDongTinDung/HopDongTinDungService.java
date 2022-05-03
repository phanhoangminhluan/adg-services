package com.adg.core.service.FileGenerator.bill.bidv.HopDongTinDung;

import com.adg.core.OfficeHandler.word.WordUtils;
import com.adg.core.OfficeHandler.word.WordWriter;
import com.adg.core.service.FileGenerator.AdgWordTableHeaderMetadata;
import com.merlin.asset.core.utils.DateTimeUtils;
import com.merlin.asset.core.utils.FileUtils;
import com.merlin.asset.core.utils.JsonUtils;
import com.merlin.asset.core.utils.MapUtils;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.time.ZonedDateTime;
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

    public HopDongTinDungService(String outputFolder, Map<String, Object> data) {
        this.wordWriter = new WordWriter(template, AdgWordTableHeaderMetadata.getHeaderMapHopDongTinDung());
        this.outputFolder = outputFolder;
        this.data = data;
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
