/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app.throttling.service;

import com.sla.app.sla.service.SLA;
import com.sla.app.sla.service.SlaService;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author asusadmin
 */
@Service
public class ThrottlingServiceImpl implements ThrottlingService {

    @Autowired
    private SlaService slaService;
    @Autowired
    private SLA defaultSLA;
    @Value("${call.for.sla.delay}")
    private long callForSLADelay;
    @Value("${default.token}")
    private String defaultToken;

    @Override
    public boolean isRequestAllowed(Optional<String> token) {
        SLA sla = defineSLA(token);
        boolean result = false;
        try {
            result = sla.getSemaphore().tryAcquire();
            return result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (result) {
                sla.getSemaphore().release();
            }
        }
    }

    private SLA defineSLA(Optional<String> token) {
        String userToken = token.orElse(defaultToken);
        try {
            return slaService.getSlaByToken(userToken).get(callForSLADelay, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            return defaultSLA;
        }
    }

}
