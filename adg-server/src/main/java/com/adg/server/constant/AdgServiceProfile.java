package com.adg.server.constant;

import com.adg.loader.AdgLoaderApplication;
import com.adg.scheduler.AdgSchedulerApplication;
import com.merlin.asset.core.starter.ServerApplication;

import java.util.function.Consumer;

public enum AdgServiceProfile {

    SCHEDULER(AdgSchedulerApplication.class, AdgSchedulerApplication::main),
    LOADER(AdgLoaderApplication.class, AdgLoaderApplication::main);

    public final Class<? extends ServerApplication> startupClass;
    public final Consumer<String[]> mainMethod;

    AdgServiceProfile(Class<? extends ServerApplication> startupClass, Consumer<String[]> mainMethod) {
        this.startupClass = startupClass;
        this.mainMethod = mainMethod;
    }
}
