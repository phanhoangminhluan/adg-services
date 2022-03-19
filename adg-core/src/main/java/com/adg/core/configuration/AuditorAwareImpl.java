package com.adg.core.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.19 05:29
 */
public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("adg-loader");
    }
}
