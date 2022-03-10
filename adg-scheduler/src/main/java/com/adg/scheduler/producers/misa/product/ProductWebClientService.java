package com.adg.scheduler.producers.misa.product;

import com.adg.scheduler.producers.misa.MisaWebClientService;
import com.merlin.asset.core.utils.MapUtils;
import com.merlin.asset.core.utils.ParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 14:18
 */
@Component
public class ProductWebClientService {
    @Autowired
    private MisaWebClientService misaWebClientService;

    public void getProducts() {

        int page = 1;
        final int pageSize = 50;

        int size;

        do {
            long t1 = System.currentTimeMillis();
            int finalPage = page;
            Map<String, Object> responseMap = misaWebClientService.get((uriBuilder -> uriBuilder
                    .path("products")
                    .queryParam("page", ParserUtils.toString(finalPage))
                    .queryParam("pageSize", ParserUtils.toString(pageSize))
                    .build()
            ));
            page++;
            List<Map<String, Object>> result = MapUtils.getListMapStringObject(responseMap, "data");
            size = result.size();
            System.out.format("Product - page: %s - size: %s - duration: %s\n", page, size, (System.currentTimeMillis() - t1) + "ms");
        } while (size != 0);

    }
}
