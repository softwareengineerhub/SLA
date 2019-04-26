/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app3.sla.service;

import com.sla.app3.semaphore.CustomSemaphore;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author asusadmin
 */
public interface SlaService {

    CompletableFuture<SLA> getSlaByToken(String token);

    public String DEFAULT_TOKEN = "DEFAULT_TOKEN";

    public static class SLA {

        private final String user;
        private final int rps;
        private final CustomSemaphore semaphore;

        public SLA(String user, int rps) {
            this.user = user;
            this.rps = rps;
            semaphore = new CustomSemaphore(rps);
        }

        public String getUser() {
            return this.user;
        }

        public int getRps() {
            return this.rps;
        }

        public CustomSemaphore getCustomSemaphore() {
            return semaphore;
        }

    }
}
