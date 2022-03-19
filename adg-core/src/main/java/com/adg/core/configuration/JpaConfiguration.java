package com.adg.core.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.18 21:04
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.adg.core.repository")
@EntityScan(basePackages = "com.adg.core.model")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
