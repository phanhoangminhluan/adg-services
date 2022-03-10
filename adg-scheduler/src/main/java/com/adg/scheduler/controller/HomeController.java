package com.adg.scheduler.controller;

import com.adg.scheduler.api.misa.MisaWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

//    @GetMapping
//    public String home() {
//        return misaWebClientService.retrieveBearerToken();
//    }

}
