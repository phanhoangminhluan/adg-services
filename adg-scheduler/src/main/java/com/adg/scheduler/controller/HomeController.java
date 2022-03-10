package com.adg.scheduler.controller;

import com.adg.scheduler.producers.misa.MisaWebClientService;
import com.adg.scheduler.producers.misa.customer.CustomerWebClientService;
import com.adg.scheduler.producers.misa.product.ProductWebClientService;
import com.adg.scheduler.producers.misa.order.SaleOrderWebClientService;
import com.adg.scheduler.producers.misa.stock.StockService;
import com.adg.scheduler.producers.misa.stock.StockWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    private StockWebClientService stockWebClientService;

    @Autowired
    private ProductWebClientService productWebClientService;

    @Autowired
    private SaleOrderWebClientService saleOrderWebClientService;

    @Autowired
    private CustomerWebClientService customerWebClientService;

    @Autowired
    private StockService stockService;

    @GetMapping("/account")
    public String home() {
        return misaWebClientService.retrieveBearerToken();
    }

    @GetMapping("/stocks")
    public String getStocks() {

        stockService.pushToKafka();

        return "ok";
    }

    @GetMapping("/products")
    public String getProducts() {
        productWebClientService.getProducts();
        return "ok";
    }

    @GetMapping("/orders")
    public String getSaleOrders() {
        saleOrderWebClientService.getSaleOrders();
        return "ok";
    }

    @GetMapping("/customers")
    public String getCustomers() {
        customerWebClientService.getCustomers();
        return "ok";
    }
}
