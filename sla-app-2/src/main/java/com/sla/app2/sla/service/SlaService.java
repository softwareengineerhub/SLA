/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app2.sla.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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
        private final BlockingQueue blockingQueue;

        public SLA(String user, int rps) {
            this.user = user;
            this.rps = rps;
            blockingQueue = new ArrayBlockingQueue(rps);
        }

        public String getUser() {
            return this.user;
        }

        public int getRps() {
            return this.rps;
        }

        public BlockingQueue getBlockingQueue() {
            return blockingQueue;
        }

    }
}
