package com.adg.core.service.InternationalPayment.bidv.reader;

import com.adg.core.OfficeHandler.excel.ExcelReader;
import com.adg.core.service.InternationalPayment.bidv.enums.HoaDonHeaderMetadata;
import com.adg.core.service.InternationalPayment.bidv.writer.BangKeSuDungTienVay.BangKeSuDungTienVayService;
import com.adg.core.service.InternationalPayment.bidv.writer.DonCamKet.DonCamKetService;
import com.merlin.asset.core.utils.MapUtils;
import com.merlin.asset.core.utils.ParserUtils;
import com.merlin.asset.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.10 21:47
 */
public class HoaDonService {

    private static final Logger logger = LoggerFactory.getLogger(HoaDonService.class);

    public List<Map<String, Object>> readHoaDonTable(String fileHoaDonPath) {
        ExcelReader excelReader = new ExcelReader(fileHoaDonPath);
        Map<String, Object> output = excelReader.readTable("A1");
        this.validate(output);

        return MapUtils.getListMapStringObject(output, "records");
    }

    public Map<String, Object> transformHoaDonTable(List<Map<String, Object>> records) {

        Map<String, Object> transformedHoaDon = this.mapByNhaCungCap(records);
        Map<String, Object> mapByNhaCungCap = MapUtils.getMapStringObject(transformedHoaDon, "Map by nhà cung cấp");

        List<Map<String, Object>> sortedListBySttKhongGop = MapUtils.getListMapStringObject(transformedHoaDon, "Số thứ tự không gộp");
        List<Map<String, Object>> sortedListBySttCoGop = MapUtils.getListMapStringObject(transformedHoaDon, "Số thứ tự có gộp");

        String outputFolder = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/output";


        BangKeSuDungTienVayService bangKeSuDungTienVayService = new BangKeSuDungTienVayService(outputFolder, sortedListBySttKhongGop);
        bangKeSuDungTienVayService.exportDocument();

        DonCamKetService donCamKetService = new DonCamKetService(outputFolder, sortedListBySttKhongGop);
        donCamKetService.exportDocument();

        return mapByNhaCungCap;

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
                        case SoThuTuCoGop: {
                            hoaDonCuThe.put(hoaDonHeaderMetadata.deAccentedName, sttGop);
                            break;
                        }
                    }
                }
            }
            sortedBySttKhongGop.add(hoaDonCuThe);
            sortedBySttCoGop.add(hoaDonCuThe);
            dsHoaDonNhaCungCap.put(soHoaDon, hoaDonCuThe);
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
