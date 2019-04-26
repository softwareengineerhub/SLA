/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app3.throttling.service;

import com.sla.app3.sla.service.SlaServiceImpl;
import com.sla.app3.sla.service.SlaService;
import static com.sla.app3.sla.service.SlaService.DEFAULT_TOKEN;
import com.sla.app3.sla.service.SlaService.SLA;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author asusadmin
 */
public class ThrottlingServiceImpl implements ThrottlingService {

    private SlaService slaService;
    private SLA defaultSLA;
    private long callForSLADelay;

    public ThrottlingServiceImpl(int guestRPS, long callForSLADelay) {
        try {
            this.callForSLADelay = callForSLADelay;
            slaService = new SlaServiceImpl(guestRPS);
            defaultSLA = slaService.getSlaByToken(DEFAULT_TOKEN).get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public ThrottlingServiceImpl(int guestRPS) {
        this(guestRPS, 50);
    }

    @Override
    public boolean isRequestAllowed(Optional<String> token) {
        SLA sla = defineSLA(token);
        boolean result = false;
        try {
            result = sla.getCustomSemaphore().tryAcquire();
            return result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (result) {
                sla.getCustomSemaphore().release();
            }
        }
    }

    private SLA defineSLA(Optional<String> token) {
        String userToken = token.orElse(DEFAULT_TOKEN);
        try {
            return slaService.getSlaByToken(userToken).get(callForSLADelay, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            return defaultSLA;
        }
    }

}
