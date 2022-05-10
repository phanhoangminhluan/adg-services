package com.adg.core.service.InternationalPayment.bidv.writer.DonCamKet;

import com.adg.core.OfficeHandler.word.WordUtils;
import com.adg.core.service.FileGenerator.AdgWordTableHeaderInfo;
import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.03 20:16
 */
public enum DonCamKetTableHeaderInfoMetadata implements AdgWordTableHeaderInfo {
    STT(
            "STT",
            0,
            cell -> {
                cell.setWidthType(TableWidthType.PCT);
                cell.setWidth("8.1%");
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                WordUtils.Table.makeCenter(cell);
            },
            runs -> runs.forEach(run -> run.setFontSize(11))
    ),
    SoHoaDon(
            "Số hoá đơn",
            1,
            cell -> {
                cell.setWidthType(TableWidthType.PCT);
                cell.setWidth("14.1%");
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                WordUtils.Table.makeCenter(cell);
            },
            runs -> runs.forEach(run -> run.setFontSize(11))
    ),
    NgayHoaDon(
            "Ngày hoá đơn",
            2,
            cell -> {
                cell.setWidthType(TableWidthType.PCT);
                cell.setWidth("15.1%");
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                WordUtils.Table.makeCenter(cell);
            },
            runs -> runs.forEach(run -> run.setFontSize(11))
    ),
    SoTienTrenHoaDon(
            "Số tiền trên hoá đơn",
            3,
            cell -> {
                cell.setWidthType(TableWidthType.PCT);
                cell.setWidth("22%");
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                WordUtils.Table.makeCenter(cell);
            },
            runs -> runs.forEach(run -> run.setFontSize(11))
    ),
    ToChucXuatHoaDon(
            "Tên, mã số thuế của đơn vị, tổ chức xuất hoá đơn",
            4,
            cell -> {
                cell.setWidthType(TableWidthType.PCT);
                cell.setWidth("40.5%");
                cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                WordUtils.Table.makeCenter(cell);
            },
            runs -> runs.forEach(run -> run.setFontSize(11))
    ),

    ;

    private final String headerName;
    private final int ordinal;
    private final Consumer<XWPFTableCell> cellFormatConsumer;
    private final Consumer<List<XWPFRun>> runConsumer;


    DonCamKetTableHeaderInfoMetadata(String headerName, int ordinal, Consumer<XWPFTableCell> cellFormatConsumer, Consumer<List<XWPFRun>> runConsumer) {
        this.headerName = headerName;
        this.ordinal = ordinal;
        this.cellFormatConsumer = cellFormatConsumer;
        this.runConsumer = runConsumer;
    }

    @Override
    public String getHeaderName() {
        return this.headerName;
    }

    @Override
    public int getOrdinal() {
        return this.ordinal;
    }

    @Override
    public Consumer<XWPFTableCell> cellFormatConsumer() {
        return this.cellFormatConsumer;
    }

    @Override
    public Consumer<List<XWPFRun>> runConsumer() {
        return this.runConsumer;
    }
}
