package com.adg.scheduler.producers.misa.stock;

import com.adg.scheduler.producers.misa.MisaWebClientService;
import com.merlin.asset.core.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 13:42
 */
@Component
public class StockWebClientService {

    @Autowired
    private MisaWebClientService misaWebClientService;

    public List<Map<String, Object>> getStocks() {
        Map<String, Object> responseMap = misaWebClientService.get((uriBuilder -> uriBuilder.path("stocks").build()));
        return MapUtils.getListMapStringObject(responseMap, "data");
    }

}
