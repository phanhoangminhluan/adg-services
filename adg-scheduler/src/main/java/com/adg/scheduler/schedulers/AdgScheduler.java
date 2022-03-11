package com.adg.scheduler.schedulers;

import com.adg.scheduler.producers.misa.customer.CustomerProducerService;
import com.adg.scheduler.producers.misa.order.SaleOrderProducerService;
import com.adg.scheduler.producers.misa.product.ProductProducerService;
import com.adg.scheduler.producers.misa.stock.StockProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 15:01
 */
@Component
public class AdgScheduler {

    @Autowired
    private CustomerProducerService customerProducerService;

    @Autowired
    private SaleOrderProducerService saleOrderProducerService;

    @Autowired
    private ProductProducerService productProducerService;

    @Autowired
    private StockProducerService stockProducerService;

    @Scheduled(fixedDelay = 15 * 60 * 1000)
    public void syncCustomers() {
        customerProducerService.fetchThenProduce();
    }

    @Scheduled(fixedDelay = 15 * 60 * 1000)
    public void syncOrders() {
        saleOrderProducerService.fetchThenProduce();
    }

    @Scheduled(fixedDelay = 15 * 60 * 1000)
    public void syncProducts() {
        productProducerService.fetchThenProduce();
    }

    @Scheduled(fixedDelay = 15 * 60 * 1000)
    public void syncStocks() {
        stockProducerService.fetchThenProduce();
    }
}
