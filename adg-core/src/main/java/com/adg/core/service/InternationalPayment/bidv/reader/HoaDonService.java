package com.adg.core.service.InternationalPayment.bidv.reader;

import com.adg.core.OfficeHandler.excel.ExcelReader;
import com.adg.core.service.InternationalPayment.bidv.enums.HoaDonHeaderMetadata;
import com.adg.core.service.InternationalPayment.bidv.enums.PhieuNhapKhoHeaderMetadata;
import com.adg.core.service.InternationalPayment.bidv.writer.BangKeSuDungTienVay.BangKeSuDungTienVayService;
import com.adg.core.service.InternationalPayment.bidv.writer.BienBanKiemTraSuDungVonVay.BienBanKiemTraSuDungVonVayService;
import com.adg.core.service.InternationalPayment.bidv.writer.DonCamKet.DonCamKetService;
import com.adg.core.service.InternationalPayment.bidv.writer.DonMuaHang.DonMuaHangService;
import com.adg.core.service.InternationalPayment.bidv.writer.HopDongTinDung.HopDongTinDungService;
import com.adg.core.service.InternationalPayment.bidv.writer.UyNhiemChi.UyNhiemChiService;
import com.merlin.asset.core.utils.DateTimeUtils;
import com.merlin.asset.core.utils.MapUtils;
import com.merlin.asset.core.utils.ParserUtils;
import com.merlin.asset.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.10 21:47
 */
public class HoaDonService {

    private static final Logger logger = LoggerFactory.getLogger(HoaDonService.class);

    public List<Map<String, Object>> readHoaDonTable(String fileHoaDonPath) {
        ExcelReader excelReader = new ExcelReader(fileHoaDonPath);
        Map<String, Object> output = excelReader.readTable("A2");
        List<Map<String, Object>> records = MapUtils.getListMapStringObject(output, "records");
        List<Map<String, Object>> actualRecords = records.stream().filter(record -> !ParserUtils.isNullOrEmpty(MapUtils.getString(record, HoaDonHeaderMetadata.SoChungTu.name, "").trim())).collect(Collectors.toList());

        this.validate(MapUtils.ImmutableMap()
                .put("headers", MapUtils.getListMapStringObject(output, "headers"))
                .put("records", actualRecords)
                .build());
        return actualRecords;
    }

    public Map<String, Object> readPhieuNhapKho(List<String> listFilePhieuNhapKho) {
        Map<String, Object> phieuNhapKhoByNCC = new HashMap<>();
        for (String filePhieuNhapKho : listFilePhieuNhapKho) {
            ExcelReader excelReader = new ExcelReader(filePhieuNhapKho);
            Map<String, Object> output = excelReader.readTable("A16");
            List<Map<String, Object>> records = MapUtils.getListMapStringObject(output, "records");
            String description = excelReader.getCellValueAsString("A10");
            Map<String, Object> phieuNhapKhoDescription = this.parsePhieuNhapKhoDescription(description);

            String ncc = MapUtils.getString(phieuNhapKhoDescription, PhieuNhapKhoHeaderMetadata.NhaCungCap.deAccentedName);
            String soHoaDon = MapUtils.getString(phieuNhapKhoDescription, PhieuNhapKhoHeaderMetadata.SoHoaDon.deAccentedName);
            Map<String, Object> donHangCuaNCC = MapUtils.getMapStringObject(phieuNhapKhoByNCC, ncc, new HashMap<>());
            List<Map<String, Object>> actualRecords = new ArrayList<>();
            for (Map<String, Object> record : records) {
                if (ParserUtils.isNullOrEmpty(MapUtils.getString(record, PhieuNhapKhoHeaderMetadata.STT.name))) {
                    continue;
                }
                if (MapUtils.getString(record, PhieuNhapKhoHeaderMetadata.STT.name).equals("- Tổng số tiền (Viết bằng chữ):")) {
                    break;
                }
                record.put(PhieuNhapKhoHeaderMetadata.NhaCungCap.deAccentedName, ncc);
                record.put(PhieuNhapKhoHeaderMetadata.SoHoaDon.deAccentedName, soHoaDon);
                actualRecords.add(record);
            }
            donHangCuaNCC.put(soHoaDon, actualRecords);
            phieuNhapKhoByNCC.put(ncc, donHangCuaNCC);
        }


        return phieuNhapKhoByNCC;
    }

    public Map<String, Object> parsePhieuNhapKhoDescription(String description) {
        Map<String, Object> output = new HashMap<>();
        List<String> arr = Arrays.asList(description.split("của"));
        String ncc = arr.get(1).trim();
        String firstPart = arr.get(0).replace("- Theo hóa đơn số", "").trim();
        List<String> arr2 = Arrays.asList(firstPart.split(" ")).stream().filter(str -> (
                !str.trim().equalsIgnoreCase("ngày") && !str.trim().equalsIgnoreCase("tháng") && !str.trim().equalsIgnoreCase("năm") && !str.trim().equalsIgnoreCase("")
        )).collect(Collectors.toList());

        String soHoaDon = arr2.get(0);
        ZonedDateTime zdt = ZonedDateTime.of(
                ParserUtils.toInt(arr2.get(3)),
                ParserUtils.toInt(arr2.get(2)),
                ParserUtils.toInt(arr2.get(1)),
                0,0,0,0, ZoneId.of("Asia/Ho_Chi_Minh")
        );

        output.put(PhieuNhapKhoHeaderMetadata.SoHoaDon.deAccentedName, soHoaDon);
        output.put(PhieuNhapKhoHeaderMetadata.NgayChungTu.deAccentedName, DateTimeUtils.convertZonedDateTimeToFormat(zdt, "Asia/Ho_Chi_Minh", DateTimeUtils.getFormatterWithDefaultValue(DateTimeUtils.FMT_03)));
        output.put(PhieuNhapKhoHeaderMetadata.NhaCungCap.deAccentedName, ncc);
        return output;
    }

    public void transformHoaDonTable(List<Map<String, Object>> records) {

        Map<String, Object> transformedHoaDon = this.mapByNhaCungCap(records);
        Map<String, Object> mapByNhaCungCap = MapUtils.getMapStringObject(transformedHoaDon, "Map by nhà cung cấp");

        List<Map<String, Object>> sortedListBySttKhongGop = MapUtils.getListMapStringObject(transformedHoaDon, "Số thứ tự không gộp");
        List<Map<String, Object>> sortedListBySttCoGop = MapUtils.getListMapStringObject(transformedHoaDon, "Số thứ tự có gộp");

        String outputFolder = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/output";


        BangKeSuDungTienVayService bangKeSuDungTienVayService = new BangKeSuDungTienVayService(outputFolder, sortedListBySttKhongGop);
        bangKeSuDungTienVayService.exportDocument();

        DonCamKetService donCamKetService = new DonCamKetService(outputFolder, sortedListBySttKhongGop);
        donCamKetService.exportDocument();

        BienBanKiemTraSuDungVonVayService bienBanKiemTraSuDungVonVayService = new BienBanKiemTraSuDungVonVayService(outputFolder, mapByNhaCungCap);
        bienBanKiemTraSuDungVonVayService.exportDocument();

        HopDongTinDungService hopDongTinDungService = new HopDongTinDungService(outputFolder, mapByNhaCungCap);
        hopDongTinDungService.exportDocument();

        for (String ncc : mapByNhaCungCap.keySet()) {
            UyNhiemChiService uyNhiemChiService = new UyNhiemChiService(outputFolder, MapUtils.getMapStringObject(mapByNhaCungCap, ncc));
            uyNhiemChiService.exportDocument();
        }
    }

    public void transformPhieuNhapKho(Map<String, Object> phieuNhapKhoMap) {
        String outputFolder = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/output";

        for (String ncc : phieuNhapKhoMap.keySet()) {
            Map<String, Object> donHangMap = MapUtils.getMapStringObject(phieuNhapKhoMap, ncc);
            for (String soHoaDon : donHangMap.keySet()) {
                DonMuaHangService donMuaHangService = new DonMuaHangService(outputFolder, MapUtils.getListMapStringObject(donHangMap, soHoaDon), soHoaDon);
                donMuaHangService.exportDocument();
            }
        }
    }

    private Map<String, Object> mapByNhaCungCap(List<Map<String, Object>> records) {
        Map<String, Object> mapByNhaCungCap = new HashMap<>();
        List<Map<String, Object>> sortedBySttKhongGop = new ArrayList<>();
        List<Map<String, Object>> sortedBySttCoGop = new ArrayList<>();
        int sttGop = 0;
        int sttKhongGop = 1;
        for (Map<String, Object> record : records) {
            Map<String, Object> deAccentedRecord = this.deAccentAllKeys(record);
            String nhaCungCap = MapUtils.getString(deAccentedRecord, HoaDonHeaderMetadata.NhaCungCap.deAccentedName);
            String soHoaDon = MapUtils.getString(deAccentedRecord, HoaDonHeaderMetadata.SoHoaDon.deAccentedName);
            Map<String, Object> dsHoaDonNhaCungCap = MapUtils.getMapStringObject(mapByNhaCungCap, nhaCungCap, new HashMap<>());
            Map<String, Object> hoaDonCuThe = MapUtils.getMapStringObject(dsHoaDonNhaCungCap, soHoaDon, new HashMap<>());

            if (dsHoaDonNhaCungCap.isEmpty()) {
                sttGop++;
            }

            for (HoaDonHeaderMetadata hoaDonHeaderMetadata : HoaDonHeaderMetadata.values()) {
                if (hoaDonHeaderMetadata.isOriginalField) {
                    hoaDonCuThe.put(hoaDonHeaderMetadata.deAccentedName, MapUtils.getString(deAccentedRecord, hoaDonHeaderMetadata.deAccentedName));
                } else {
                    switch (hoaDonHeaderMetadata) {
                        case SoThuTuKhongGop: {
                            hoaDonCuThe.put(hoaDonHeaderMetadata.deAccentedName, sttKhongGop);
                            break;
                        }
                    }
                }
            }
            sortedBySttKhongGop.add(hoaDonCuThe);
            sortedBySttCoGop.add(hoaDonCuThe);

            List<String> listSoHoaDon = MapUtils.getListString(dsHoaDonNhaCungCap, HoaDonHeaderMetadata.ListSoHoaDon.deAccentedName, new ArrayList<>());
            listSoHoaDon.add(soHoaDon);
            double tongSoTienThanhToanCacHoaDon = MapUtils.getDouble(dsHoaDonNhaCungCap, HoaDonHeaderMetadata.TongTienThanhToanCacHoaDon.deAccentedName, 0);
            tongSoTienThanhToanCacHoaDon += MapUtils.getDouble(hoaDonCuThe, HoaDonHeaderMetadata.TongTienThanhToan.deAccentedName);


            dsHoaDonNhaCungCap.put(soHoaDon, hoaDonCuThe);
            dsHoaDonNhaCungCap.put(HoaDonHeaderMetadata.ListSoHoaDon.deAccentedName, listSoHoaDon);
            dsHoaDonNhaCungCap.put(HoaDonHeaderMetadata.TongTienThanhToanCacHoaDon.deAccentedName, tongSoTienThanhToanCacHoaDon);
            dsHoaDonNhaCungCap.put(HoaDonHeaderMetadata.SoThuTuCoGop.deAccentedName, sttGop);
            dsHoaDonNhaCungCap.put(HoaDonHeaderMetadata.NhaCungCap.deAccentedName, nhaCungCap);

            mapByNhaCungCap.put(nhaCungCap, dsHoaDonNhaCungCap);

            sttKhongGop++;
        }

        sortedBySttKhongGop.sort(Comparator.comparingInt(m -> MapUtils.getInt(m, HoaDonHeaderMetadata.SoThuTuKhongGop.deAccentedName)));
        sortedBySttCoGop.sort(Comparator.comparingInt(m -> MapUtils.getInt(m, HoaDonHeaderMetadata.SoThuTuCoGop.deAccentedName)));

        return MapUtils.ImmutableMap()
                .put("Số thứ tự có gộp", sortedBySttCoGop)
                .put("Số thứ tự không gộp", sortedBySttKhongGop)
                .put("Map by nhà cung cấp", mapByNhaCungCap)
                .build();
    }


    private void validate(Map<String, Object> output) {
        List<String> headerErrorMessages = this.validateHeaders(MapUtils.getListMapStringObject(output, "headers"));
        List<String> recordErrorMessages = this.validateRecords(MapUtils.getListMapStringObject(output, "records"));
        boolean hasError = false;

        if (!headerErrorMessages.isEmpty()) {
            hasError = true;
            headerErrorMessages.forEach(logger::error);
        }

        if (!recordErrorMessages.isEmpty()) {
            hasError = true;
            recordErrorMessages.forEach(logger::error);
        }

        if (hasError) {
            throw new IllegalArgumentException(String.format("There are %s errors from HeaderValidation and %s errors from RecordValidation", headerErrorMessages.size(), recordErrorMessages.size()));
        }

    }

    private List<String> validateHeaders(List<Map<String, Object>> headers) {

        List<String> messages = new ArrayList<>();

        for (HoaDonHeaderMetadata hoaDonHeaderMetadata : HoaDonHeaderMetadata.values()) {
            boolean found = false;
            if (!hoaDonHeaderMetadata.isOriginalField) {
                continue;
            }
            for (Map<String, Object> headerMap : headers) {
                if (StringUtils
                        .deAccent(MapUtils.getString(headerMap, "name"))
                        .equals(StringUtils.deAccent(hoaDonHeaderMetadata.name))) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                messages.add(String.format("Header named '%s' is not found", hoaDonHeaderMetadata.name));
            }
        }

        return messages;
    }

    private List<String> validateRecords(List<Map<String, Object>> records) {
        List<String> messages = new ArrayList<>();

        for (Map<String, Object> record : records) {
            Map<String, Object> deAccentedRecord = this.deAccentAllKeys(record);
            for (HoaDonHeaderMetadata hoaDonHeaderMetadata : HoaDonHeaderMetadata.values()) {
                String val = MapUtils.getString(deAccentedRecord, hoaDonHeaderMetadata.deAccentedName, null);
                if (ParserUtils.isNullOrEmpty(val)) {
                    if (!hoaDonHeaderMetadata.isNullable) {
                        messages.add(String.format(
                                "%s: Value of '%s' cannot be null or empty",
                                MapUtils.getString(deAccentedRecord, HoaDonHeaderMetadata.SoChungTu.deAccentedName),
                                hoaDonHeaderMetadata.name)
                        );
                    }
                } else {
                    if (!hoaDonHeaderMetadata.type.verifyMethod.apply(val)) {
                        messages.add(String.format(
                                    "%s: Value of '%s' which is '%s' cannot be formatted as %s",
                                    MapUtils.getString(deAccentedRecord, HoaDonHeaderMetadata.SoChungTu.deAccentedName),
                                    hoaDonHeaderMetadata.name,
                                    MapUtils.getString(deAccentedRecord, hoaDonHeaderMetadata.deAccentedName),
                                    hoaDonHeaderMetadata.type.javaType.getSimpleName())
                                );
                    }
                }
            }
        }
        return messages;
    }

    private Map<String, Object> deAccentAllKeys(Map<String, Object> record) {
        Map<String, Object> deAccentedRecord = new HashMap<>();
        record.forEach((key, val) -> deAccentedRecord.put(StringUtils.deAccent(key), val));
        return deAccentedRecord;
    }

}
