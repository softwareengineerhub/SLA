/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app4.blocking.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author asusadmin
 */
public class CustomBlockingQueue {

    private int currentlyUsed;
    private int permitLimit;
    private Lock lock;
    private Condition condition;

    public CustomBlockingQueue(int permitLimit) {
        this.permitLimit = permitLimit;
    }

    public boolean tryOffer(Object value) {
        boolean res = false;
        try {
            res = lock.tryLock();
            if (res) {
                currentlyUsed++;
                condition.signalAll();
            }
            return res;
        } finally {
            if (res) {
                lock.unlock();
            }
        }
    }

    public void offer(Object value) {
        try {
            lock.lock();
            currentlyUsed++;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void poll() {
        try {
            lock.lock();
            currentlyUsed++;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
