package com.adg.scheduler;

import com.merlin.asset.core.starter.ServerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.adg.scheduler", "com.adg.core"}
)
public class AdgSchedulerApplication extends ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdgSchedulerApplication.class, args);
    }
}
