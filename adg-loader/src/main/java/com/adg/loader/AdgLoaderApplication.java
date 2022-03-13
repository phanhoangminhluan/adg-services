package com.adg.loader;

import com.merlin.asset.core.starter.ServerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.adg.loader", "com.adg.core"}
)
public class AdgLoaderApplication extends ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdgLoaderApplication.class, args);
    }
}
