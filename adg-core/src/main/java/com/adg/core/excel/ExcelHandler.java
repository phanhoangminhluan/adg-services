package com.adg.core.excel;

import com.merlin.asset.core.utils.JsonUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.04.20 23:49
 */
public class ExcelHandler {

    public static void readToKhaiHaiQuan() {



    }

    @SneakyThrows
    public static void main(String[] args) {
        String val = FileUtils.readFileToString(new File(ExcelReader.TO_KHAI_HAI_QUAN));

        Map<String, Object> addressMap = JsonUtils.fromJson(val, JsonUtils.TYPE_TOKEN.MAP_STRING_OBJECT.type);

        ExcelReader excelReader = new ExcelReader("/Users/luan.phm/engineering/Projects/ADongGroup/adg-services/adg-api/src/main/resources/ToKhaiHQ7N_104654188660.xls");
        excelReader.openSheet("Tờ khai nhập");
        Map<String, Object> result = excelReader.getCellValues(addressMap);
        System.out.println(JsonUtils.toJson(result));
    }

}
