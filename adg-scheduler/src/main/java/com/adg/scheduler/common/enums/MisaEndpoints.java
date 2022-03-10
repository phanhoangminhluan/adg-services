package com.adg.scheduler.common.enums;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.09 16:41
 */
public enum MisaEndpoints {

    ACCOUNT("/account");
    public final String uri;

    MisaEndpoints(String uri) {
        this.uri = uri;
    }
}
