package com.adg.server;

import com.adg.server.constant.AdgServiceProfile;
import com.merlin.asset.core.starter.ServerApplication;

public class AdgServerApplication extends ServerApplication {

    public static void main(String[] args) {
        String profile = args[0].toUpperCase();
        AdgServiceProfile.valueOf(profile).mainMethod.accept(args);
//        SpringApplication.run(AdgServiceProfile.valueOf(profile).startupClass, args);
    }

}
