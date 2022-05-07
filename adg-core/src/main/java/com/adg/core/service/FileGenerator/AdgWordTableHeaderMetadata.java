package com.adg.core.service.FileGenerator;

import com.adg.core.service.FileGenerator.bill.bidv.DonCamKet.DonCamKetTableHeaderInfoMetadata;
import com.adg.core.service.FileGenerator.bill.bidv.HopDongTinDung.HopDongTinDungTableHeaderInfoMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.02 23:47
 */
public class AdgWordTableHeaderMetadata {

    public static Map<Integer, AdgWordTableHeaderInfo> getHeaderMapHopDongTinDung() {
        Map<Integer, AdgWordTableHeaderInfo> headerMap = new HashMap<>();
        for (HopDongTinDungTableHeaderInfoMetadata value : HopDongTinDungTableHeaderInfoMetadata.values()) {
            headerMap.put(value.getOrdinal(), value);
        }
        return headerMap;
    }

    public static Map<Integer, AdgWordTableHeaderInfo> getHeaderMapDonCamKet() {
        Map<Integer, AdgWordTableHeaderInfo> headerMap = new HashMap<>();

        for (DonCamKetTableHeaderInfoMetadata value : DonCamKetTableHeaderInfoMetadata.values()) {
            headerMap.put(value.getOrdinal(), value);
        }
        return headerMap;
    }
}
