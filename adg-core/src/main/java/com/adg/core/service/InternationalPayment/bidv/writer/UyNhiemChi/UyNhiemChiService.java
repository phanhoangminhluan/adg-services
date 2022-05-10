package com.adg.core.service.InternationalPayment.bidv.writer.UyNhiemChi;

import com.adg.core.OfficeHandler.word.WordWriter;
import com.merlin.asset.core.utils.DateTimeUtils;
import com.merlin.asset.core.utils.FileUtils;
import com.merlin.asset.core.utils.JsonUtils;

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

    private final static String template = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/UỶ NHIỆM CHI.docx";

    public UyNhiemChiService(String outputFolder, Map<String, Object> data) {
        this.wordWriter = new WordWriter(template, new HashMap<>());
        this.outputFolder = outputFolder;
        this.data = data;
    }

    public void exportDocument() {
        this.wordWriter.fillTextData(data);
        this.build();
    }

    private void build() {
        String fileName = String.format("Uỷ nhiệm chi - %s.docx",
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
