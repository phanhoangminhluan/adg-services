package com.adg.core.service.InternationalPayment.bidv;

import com.adg.core.service.InternationalPayment.bidv.reader.HoaDonService;

import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.10 21:52
 */
public class BidvPaymentService {

    private final static String HOA_DON_SAMPLE = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/SampleData/HOÁ ĐƠN.xlsx";

    public void exportDocuments(String fileHoaDon) {
        HoaDonService hoaDonService = new HoaDonService();

        List<Map<String, Object>> records = hoaDonService.readHoaDonTable(fileHoaDon);

        Map<String, Object> transformedRecords = hoaDonService.transformHoaDonTable(records);

    }


    public static void main(String[] args) {
        BidvPaymentService bidvPaymentService = new BidvPaymentService();
        bidvPaymentService.exportDocuments(HOA_DON_SAMPLE);

    }

}
