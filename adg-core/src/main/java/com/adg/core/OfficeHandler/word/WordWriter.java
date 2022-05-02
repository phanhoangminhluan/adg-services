package com.adg.core.OfficeHandler.word;

import com.adg.core.service.FileGenerator.AdgWordTableHeaderInfo;
import com.merlin.asset.core.utils.MapUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.01 16:55
 */
@Getter
public class WordWriter {

    private final XWPFDocument document;
    private final WordTable wordTable;

    private final InputStream inputStream;

    @SneakyThrows
    public WordWriter(String template, Map<Integer, AdgWordTableHeaderInfo> headerMap) {
        this.inputStream = Files.newInputStream(Paths.get(template));
        this.document = new XWPFDocument(this.inputStream);
        this.wordTable = new WordTable(this.document, headerMap);
    }

    public void fillTextData(Map<String, Object> data) {
        for (IBodyElement bodyElement : this.document.getBodyElements()) {
            if (bodyElement instanceof XWPFParagraph) {
                XWPFParagraph paragraph = (XWPFParagraph) bodyElement;
                for (XWPFRun run : paragraph.getRuns()) {
                    String val = run.getText(0);
                    if (val != null && val.contains("{{") && val.contains("}}")) {
                        String key = val.substring(val.indexOf("{{") + 2, val.indexOf("}}"));
                        String newText = val.replace("{{" + key + "}}", MapUtils.getString(data, key));
                        run.setText(newText, 0);
                    }
                }
            }
        }
    }

    public void fillTableData(List<Map<String, Object>> records) {
        for (Map<String, Object> record : records) {
            this.wordTable.insertRecord(record);
        }
    }

    @SneakyThrows
    public void build(String outputFile) {
        this.document.write(Files.newOutputStream(Paths.get(outputFile)));
        this.inputStream.close();
    }
}
