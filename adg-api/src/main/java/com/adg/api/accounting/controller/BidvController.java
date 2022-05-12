package com.adg.api.accounting.controller;

import com.adg.core.service.InternationalPayment.bidv.reader.HoaDonService;
import com.adg.core.utils.ZipUtil;
import com.merlin.asset.core.utils.JsonUtils;
import com.merlin.asset.core.utils.MapUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.05.12 02:25
 */
@RestController
@RequestMapping("/internal/internation-payment/disbursement/bidv/")
public class BidvController {

    @Autowired
    private HoaDonService hoaDonService;

    @PostMapping("import")
    @SneakyThrows
    public String importFile(@RequestParam("file") MultipartFile file) {
        List<File> files = new ArrayList<>();
        try {
            files = ZipUtil.uncompressZipFile( file.getInputStream(),"/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/outputzip");
            String fileHoaDon = "";
            List<String> filePNK = new ArrayList<>();
            for (File f : files) {
                if (f.getName().startsWith("hd")) {
                    fileHoaDon = f.getAbsolutePath();
                } else {
                    filePNK.add(f.getAbsolutePath());
                }
            }
            List<Map<String, Object>> hoaDonMap = this.hoaDonService.readHoaDonTable(fileHoaDon);
            Map<String, Object> pnkMap = this.hoaDonService.readPhieuNhapKho(filePNK);

            return JsonUtils.toJson(MapUtils.ImmutableMap()
                            .put("data", MapUtils.ImmutableMap()
                                    .put("hd", hoaDonMap)
                                    .put("pnk", pnkMap)
                                    .build())
                            .put("status", "ok")
                    .build());
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            files.stream().forEach(f -> f.delete());
        }
        return JsonUtils.toJson(MapUtils.ImmutableMap()
                .put("data", MapUtils.ImmutableMap().build())
                .put("status", "error")
                .build());
    }

    @PostMapping(value = "export",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public byte[] exportFile(@RequestBody Map<String, Object> request) {
        try {
            Map<String, Object> data = MapUtils.getMapStringObject(request, "data");
            List<Map<String, Object>> hd = MapUtils.getListMapStringObject(data, "hd");
            System.out.println(JsonUtils.toJson(hd));
            Map<String, Object> pnk = MapUtils.getMapStringObject(data, "pnk");
            System.out.println(JsonUtils.toJson(pnk));
            return this.hoaDonService.exportDocuments(hd, pnk);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
