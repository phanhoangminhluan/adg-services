package com.adg.core.excel;

import com.google.gson.internal.LinkedTreeMap;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.19 21:35
 */
@Data
public class ExcelReader {

    private String filePath;
    private Workbook workbook;
    private FileInputStream fileInputStream;
    private Sheet sheet;

    public final static String TO_KHAI_HAI_QUAN = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/ToKhaiHaiQuan.json";

    @SneakyThrows
    public ExcelReader(String filePath) {
        this.filePath = filePath;
        this.fileInputStream = new FileInputStream(filePath);
        this.workbook = new HSSFWorkbook(fileInputStream);
    }

    public void openSheet(String sheetName) {
        this.sheet = this.workbook.getSheetAt(0);
    }

    public String getCellValue(String cellAddress) {
        Cell cell = this.getCell(cellAddress);
        return ExcelUtils.parseString(cell);
    }
    public List<String> getCellValues(List<String> cellAddresses) {
        return cellAddresses
                .stream()
                .map(this::getCellValue).collect(Collectors.toList());
    }

    @SneakyThrows
    public static void main(String[] args) {

    }

    @SneakyThrows
    public Map<String, Object> getCellValues(Map<String, Object> addressMap) {
        Map<String, Object> result = new HashMap<>();
        this.handleMap(result, "data", addressMap);
        return result;
    }

    private void handleMap(Map<String, Object> result, String keyAddress, Map<String, Object> addressMap) {
        Map<String, Object> childResult = new HashMap<>();
        addressMap.forEach((key, value) -> {
            if (value instanceof String) {
                String cellAddress = (String) value;
                childResult.put(key, this.getCellValue(cellAddress));
            }

            if (value instanceof ArrayList) {
                List<String> cellAddresses = (List<String>) value;
                childResult.put(key, this.getCellValues(cellAddresses));
            }

            if (value instanceof LinkedTreeMap) {
                Map<String, Object> valMap = (Map<String, Object>) value;
                this.handleMap(childResult, key, valMap);
            }
        });
        result.put(keyAddress, childResult);
    }



    public Cell getCell(String cellAddress) {
        CellReference cellReference = new CellReference(cellAddress);
        return this.sheet
                .getRow(cellReference.getRow())
                .getCell(cellReference.getCol());
    }
}
