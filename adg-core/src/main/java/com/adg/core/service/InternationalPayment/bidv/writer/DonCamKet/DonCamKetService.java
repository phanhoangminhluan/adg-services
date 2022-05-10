package com.adg.core.service.InternationalPayment.bidv.writer.DonCamKet;

import com.adg.core.OfficeHandler.word.WordWriter;
import com.adg.core.service.FileGenerator.AdgWordTableHeaderMetadata;
import com.merlin.asset.core.utils.DateTimeUtils;
import com.merlin.asset.core.utils.FileUtils;
import com.merlin.asset.core.utils.JsonUtils;
import com.merlin.asset.core.utils.MapUtils;

import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.03 20:24
 */
public class DonCamKetService {

    private final WordWriter wordWriter;
    private final String outputFolder;
    private final Map<String, Object> data;

    private final static String template = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/ĐƠN CAM KẾT.docx";

    public DonCamKetService(String outputFolder, Map<String, Object> data) {
        this.wordWriter = new WordWriter(template, AdgWordTableHeaderMetadata.getHeaderMapDonCamKet());
        this.outputFolder = outputFolder;
        this.data = data;
    }

    public void exportDocument() {
        this.fillTextData();
        this.fillTableData();
        this.build();
    }

    private void fillTextData() {
        this.wordWriter.fillTextData(data);
    }

    private void fillTableData() {
        this.wordWriter.fillTableData(MapUtils.getListMapStringObject(this.data, "Danh mục hoá đơn diện tử"));
    }

    private void build() {
        String fileName = String.format("Đơn cam kết - %s.docx",
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

        String sampleDataFile = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/SampleData/ĐƠN CAM KẾT.json";
        String json = FileUtils.readContent(sampleDataFile);
        Map<String, Object> data = JsonUtils.fromJson(json, JsonUtils.TYPE_TOKEN.MAP_STRING_OBJECT.type);

        DonCamKetService donCamKetService = new DonCamKetService(outputFolder, data);
        donCamKetService.exportDocument();
    }

}
