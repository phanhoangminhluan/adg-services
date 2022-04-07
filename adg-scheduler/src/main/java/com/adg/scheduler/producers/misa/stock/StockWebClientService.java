package com.adg.scheduler.producers.misa.stock;

import com.adg.scheduler.producers.misa.AbstractWebClientService;
import com.merlin.asset.core.utils.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 13:42
 */
@Component
public class StockWebClientService extends AbstractWebClientService {

    @Override
    public List<Map<String, Object>> fetchItems(int page) {
        Map<String, Object> responseMap = this.misaWebClientService.get((uriBuilder -> uriBuilder.path(this.getUriPath()).build()));
        return MapUtils.getListMapStringObject(responseMap, "data");
    }

    @Override
    protected String getUriPath() {
        return "stocks";
    }
}
