package com.adg.server.constant;

import com.adg.loader.AdgLoaderApplication;
import com.adg.scheduler.AdgSchedulerApplication;
import com.merlin.asset.core.starter.ServerApplication;

public enum AdgServiceProfile {

    SCHEDULER(AdgSchedulerApplication.class),
    LOADER(AdgLoaderApplication.class);

    public final Class<? extends ServerApplication> startupClass;

    AdgServiceProfile(Class<? extends ServerApplication> startupClass) {
        this.startupClass = startupClass;
    }
}
