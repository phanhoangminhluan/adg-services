package com.adg.scheduler;

import com.merlin.asset.core.starter.ServerApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.adg.scheduler", "com.adg.core"}
)
public class AdgSchedulerApplication extends ServerApplication {



}
