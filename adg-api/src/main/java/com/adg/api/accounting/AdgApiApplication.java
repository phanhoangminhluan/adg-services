package com.adg.api.accounting;

import com.merlin.asset.core.starter.ServerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.adg.api", "com.adg.core"}
)
public class AdgApiApplication extends ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdgApiApplication.class, args);
    }


}
