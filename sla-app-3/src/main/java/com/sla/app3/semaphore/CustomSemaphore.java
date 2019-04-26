/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app3.semaphore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author asusadmin
 */
public class CustomSemaphore {

    private int currentlyUsed;
    private int permitLimit;
    private Lock lock;
    private Condition condition;

    public CustomSemaphore(int permits) {
        this.permitLimit = permits;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public boolean tryAcquire() {
        boolean res = false;
        try {
            res = lock.tryLock();
            if (res) {
                currentlyUsed++;
            }
            return res;
        } finally {
            if (res) {
                lock.unlock();
            }
        }
    }

    public void acquire() {
        try {
            lock.lock();
            while (currentlyUsed == permitLimit) {
                condition.await();
            }
            currentlyUsed++;
            condition.notifyAll();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        try {
            lock.lock();
            while (currentlyUsed == 0) {
                condition.await();
            }
            currentlyUsed--;
            notify();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }

    }

}
