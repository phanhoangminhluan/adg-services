package com.adg.scheduler.controller;

import com.adg.scheduler.producers.misa.MisaWebClientService;
import com.adg.scheduler.producers.misa.customer.CustomerProducerService;
import com.adg.scheduler.producers.misa.order.SaleOrderProducerService;
import com.adg.scheduler.producers.misa.product.ProductProducerService;
import com.adg.scheduler.producers.misa.stock.StockProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.09 14:10
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private MisaWebClientService misaWebClientService;

    @Autowired
    private StockProducerService stockProducerService;

    @Autowired
    private ProductProducerService productProducerService;

    @Autowired
    private SaleOrderProducerService saleOrderProducerService;

    @Autowired
    private CustomerProducerService customerProducerService;

    @GetMapping("/account")
    public String home() {
        return misaWebClientService.retrieveBearerToken();
    }

    @GetMapping("/stocks")
    public String getStocks() {
        this.stockProducerService.fetchThenProduce();
        return "ok";
    }

    @GetMapping("/products")
    public String getProducts() {
        productProducerService.fetchThenProduce();
        return "ok";
    }

    @GetMapping("/orders")
    public String getSaleOrders() {
        saleOrderProducerService.fetchThenProduce();
        return "ok";
    }

    @GetMapping("/customers")
    public String getCustomers() {
        customerProducerService.fetchThenProduce();
        return "ok";
    }
}
