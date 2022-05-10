package com.adg.core.service.InternationalPayment.bidv;

import com.adg.core.service.InternationalPayment.bidv.reader.HoaDonService;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.10 21:52
 */
public class BidvPaymentService {

    private final static String HOA_DON_SAMPLE = "/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/bidv/SampleData/HOÁ ĐƠN.xlsx";

    public void exportDocuments(String fileHoaDon) {
        HoaDonService hoaDonService = new HoaDonService();
        hoaDonService.readHoaDonTable(fileHoaDon);
    }


    public static void main(String[] args) {
        BidvPaymentService bidvPaymentService = new BidvPaymentService();
        bidvPaymentService.exportDocuments(HOA_DON_SAMPLE);

    }

}
