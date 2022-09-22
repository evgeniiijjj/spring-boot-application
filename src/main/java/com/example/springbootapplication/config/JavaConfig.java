package com.example.springbootapplication.config;

import com.example.springbootapplication.profiles.DevProfile;
import com.example.springbootapplication.profiles.ProductionProfile;
import com.example.springbootapplication.profiles.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "prod")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
