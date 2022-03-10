package com.adg.scheduler.producers.misa.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 00:04
 */
@Component
public class StockService {

    @Autowired
    private StockWebClientService stockWebClientService;

    @Autowired
    private StockProducerService stockProducerService;

    public void pushToKafka() {
        List<Map<String, Object>> result = stockWebClientService.getStocks();

        stockProducerService.produce(result);
    }

}
