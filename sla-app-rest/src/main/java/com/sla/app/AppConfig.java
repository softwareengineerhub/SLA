/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app;

import com.sla.app.sla.service.SLA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * @author asusadmin
 */
@Configuration
@EnableAsync
@PropertySource(value = "classpath:app-config.properties")
public class AppConfig {

    @Value("${guest.rps}")
    private int guestRPS;
    @Value("${default.token}")
    private String defaultToken;

    @Bean
    public SLA defaultSLA() {
        return new SLA(defaultToken, guestRPS);
    }
}
