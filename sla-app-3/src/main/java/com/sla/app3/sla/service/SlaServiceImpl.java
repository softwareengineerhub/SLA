/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app3.sla.service;

import com.sla.app3.cache.CacheService;
import com.sla.app3.cache.CacheServiceImpl;
import com.sla.app3.dao.DaoImpl;
import com.sla.app3.dao.Dao;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author asusadmin
 */
public class SlaServiceImpl implements SlaService {

    private Dao dao;
    private CacheService cacheService;
    private int tokensCount = 10;
    private int guestRPS;
    private SLA defaultSLA;

    public SlaServiceImpl(int guestRPS) {
        defaultSLA = new SLA(DEFAULT_TOKEN, guestRPS);
        this.guestRPS = guestRPS;
        dao = new DaoImpl(tokensCount);
        cacheService = new CacheServiceImpl();
    }

    @Override
    public CompletableFuture<SLA> getSlaByToken(String token) {
        return CompletableFuture.supplyAsync(() -> {
            SLA sla = cacheService.get(token);
            if (Objects.isNull(sla)) {
                sla = dao.read(token);
                cacheService.put(token, sla);
            }
            return sla;
        });

    }

}
