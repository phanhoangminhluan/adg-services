package com.adg.core.service.FileGenerator.bill.bidv.HopDongTinDung;

import com.adg.core.OfficeHandler.word.WordUtils;
import com.adg.core.service.FileGenerator.AdgWordTableHeaderInfo;
import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.02 10:24
 */
public enum HopDongTinDungTableHeaderInfoMetadata implements AdgWordTableHeaderInfo {
    STT("STT", 0, cell -> {
        cell.setWidthType(TableWidthType.PCT);
        cell.setWidth("7.1%");
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        WordUtils.Table.makeCenter(cell);
    }, runs -> runs.forEach(run -> run.setFontSize(11))),
    NoiDung("Nội dung", 1, cell -> {
        cell.setWidthType(TableWidthType.PCT);
        cell.setWidth("18.1%");
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        WordUtils.Table.makeCenter(cell);

    }, runs -> runs.forEach(run -> run.setFontSize(11))),
    SoHieuChungTuKeToan("Số hiệu chứng từ kế toán", 2, cell -> {
        cell.setWidthType(TableWidthType.PCT);
        cell.setWidth("12.6%");
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        WordUtils.Table.makeCenter(cell);

    }, runs -> runs.forEach(run -> run.setFontSize(11))),
    SoTienVND("Số tiền (VND)", 3, cell -> {
        cell.setWidthType(TableWidthType.PCT);
        cell.setWidth("23.1%");
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        WordUtils.Table.makeCenter(cell);

    }, runs -> runs.forEach(run -> run.setFontSize(11))),
    TenDonViSoTaiKhoan("Tên đơn vị, số tài khoản, Ngân hàng người thụ hưởng", 4, cell -> {
        cell.setWidthType(TableWidthType.PCT);
        cell.setWidth("38.9%");
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        WordUtils.Table.makeCenter(cell);

    }, runs -> runs.forEach(run -> run.setFontSize(11)))
    ;

    private final String headerName;
    private final int ordinal;
    private final Consumer<XWPFTableCell> cellFormatConsumer;
    private final Consumer<List<XWPFRun>> runConsumer;

    HopDongTinDungTableHeaderInfoMetadata(String headerName, int ordinal, Consumer<XWPFTableCell> cellFormatConsumer, Consumer<List<XWPFRun>> runConsumer) {
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
