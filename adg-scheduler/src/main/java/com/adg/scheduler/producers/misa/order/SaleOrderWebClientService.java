package com.adg.scheduler.producers.misa.order;

import com.adg.scheduler.producers.misa.AbstractWebClientService;
import com.merlin.asset.core.utils.MapUtils;
import com.merlin.asset.core.utils.ParserUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 14:18
 */
@Component
public class SaleOrderWebClientService extends AbstractWebClientService {

    protected String getUriPath() {
        return "SaleOrders";
    }

    @Override
    public List<Map<String, Object>> fetchItems(int page) {
        long t1 = System.currentTimeMillis();
        Map<String, Object> responseMap = this.misaWebClientService.get((uriBuilder -> uriBuilder
                .path(this.getUriPath())
                .queryParam("page", ParserUtils.toString(page))
                .queryParam("pageSize", ParserUtils.toString(PAGE_SIZE))
                .queryParam("orderBy", "createdDate")
                .build()
        ));
        List<Map<String, Object>> result = MapUtils.getListMapStringObject(responseMap, "data");
        return result;
    }
}
