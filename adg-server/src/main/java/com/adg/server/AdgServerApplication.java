package com.adg.server;

import com.adg.server.constant.AdgServiceProfile;
import com.merlin.asset.core.starter.ServerApplication;
import org.springframework.boot.SpringApplication;

public class AdgServerApplication extends ServerApplication {

    public static void main(String[] args) {
        String profile = args[0].toUpperCase();
        SpringApplication.run(AdgServiceProfile.valueOf(profile).startupClass, args);
    }

}
