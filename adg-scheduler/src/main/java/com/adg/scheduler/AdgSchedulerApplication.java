package com.adg.scheduler;

import com.adg.core.service.slack.SlackService;
import com.merlin.asset.core.starter.ServerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.adg.scheduler", "com.adg.core"}
)
public class AdgSchedulerApplication extends ServerApplication {

    @Autowired
    private SlackService slackService;

    public static void main(String[] args) {
        SpringApplication.run(AdgSchedulerApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void run() {
//        slackService.sendNotification(Module.ADG_SCHEDULER, SlackAuthor.LUAN_PHAN, "On Application Startup", "Application has been started");
//    }
}
