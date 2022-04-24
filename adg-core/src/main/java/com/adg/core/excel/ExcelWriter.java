package com.adg.core.excel;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.21 22:53
 */
public class ExcelWriter {

    private String filePath;
    private Workbook workbook;
    private FileInputStream fileInputStream;
    private Sheet sheet;

    @SneakyThrows
    public ExcelWriter(String filePath) {
        this.filePath = filePath;
        this.fileInputStream = new FileInputStream(filePath);
        this.workbook = new XSSFWorkbook(fileInputStream);
    }

    public void openSheet(String sheetName) {
        this.sheet = this.workbook.getSheetAt(0);
    }

    public void setCellValue(String cellAddress, String cellValue) {
        Cell cell = this.getCell(cellAddress);
        cell.setCellValue(cellValue);
    }

    public void copyCellValue(Cell sourceCell, Cell targetCell) {
        targetCell.setCellValue(ExcelRowUtils.parseString(sourceCell));
    }

    public Row copyRow(String startCellAddress,  int columnSize) {
        Cell startCell = this.getCell(startCellAddress);

        List<Cell> sourceCells = this.getCellsByRange(startCellAddress, columnSize);

        this.sheet.shiftRows(startCell.getRow().getRowNum() + 1, startCell.getRow().getRowNum() + 2, 1, true, true);
        Row targetRow = this.sheet.createRow(startCell.getRow().getRowNum() + 1);

        for (Cell sourceCell : sourceCells) {
            Cell targetCell = targetRow.createCell(sourceCell.getColumnIndex());
            copyCellValue(sourceCell, targetCell);
            targetCell.setCellStyle(sourceCell.getCellStyle());
        }

        return targetRow;
    }

    public List<Cell> getCellsByRange(String startCellAddress, int columnSize) {
        Cell startCell = this.getCell(startCellAddress);
        int endCellIndex = startCell.getColumnIndex() + columnSize - 1;

        Cell endCell = startCell.getRow().getCell(endCellIndex) != null
                ? startCell.getRow().getCell(endCellIndex)
                : startCell.getRow().createCell(endCellIndex);

        List<Cell> cells = new ArrayList<>();

        for (Cell cell : startCell.getRow()) {
            if (cell.getColumnIndex() < startCell.getColumnIndex()) continue;
            if (cell.getColumnIndex() > endCell.getColumnIndex()) break;

            cells.add(cell);
        }

        return cells;
    }

    public Cell getCell(String cellAddress) {
        CellReference cellReference = new CellReference(cellAddress);
        return this.sheet
                .getRow(cellReference.getRow())
                .getCell(cellReference.getCol());
    }

    @SneakyThrows
    public static void main(String[] args) {
        ExcelWriter excelWriter = new ExcelWriter("/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/2. Kê Điện Tử.xlsx");
        excelWriter.openSheet("1");
        Row row = excelWriter.copyRow("A10", 6);

        excelWriter.workbook.write(new FileOutputStream("/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/2. Kê Điện Tử - output.xlsx"));
        row.setRowNum(36);
    }

}
