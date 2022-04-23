package com.adg.core.excel;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
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
        sourceCell.setCellValue(ExcelRowUtils.parseString(targetCell));
    }

    public Row copyRow(String startCellAddress, String endCellAddress) {
        Cell startCell = this.getCell(startCellAddress);
        Cell endCell = this.getCell(endCellAddress);

        List<Cell> originalCells = this.getCellsByRange(startCellAddress, endCellAddress);

        Row targetRow = this.sheet.createRow(startCell.getRow().getRowNum() + 1);

        int i = 0;
        for (Cell cell : targetRow) {

            if (cell.getColumnIndex() < startCell.getColumnIndex()) continue;
            if (cell.getColumnIndex() > endCell.getColumnIndex()) break;

            Cell originalCell = originalCells.get(i);

            copyCellValue(originalCell, cell);

            cell.setCellStyle(originalCell.getCellStyle());

            i++;
        }

        return targetRow;
    }

    public List<Cell> getCellsByRange(String startCellAddress, String endCellAddress) {
        Cell startCell = this.getCell(startCellAddress);
        Cell endCell = this.getCell(endCellAddress);

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

}
