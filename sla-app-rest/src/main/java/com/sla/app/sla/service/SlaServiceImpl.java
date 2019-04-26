/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app.sla.service;

import com.sla.app.cache.CacheService;
import com.sla.app.dao.Dao;
import java.util.Objects;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

/**
 *
 * @author asusadmin
 */
@Service
public class SlaServiceImpl implements SlaService {

    @Autowired
    private Dao dao;
    @Autowired
    private CacheService cacheService;

    @Override
    @Async
    public Future<SLA> getSlaByToken(String token) {
        SLA sla = cacheService.get(token);
        if (Objects.isNull(sla)) {
            sla = dao.read(token);
            cacheService.put(token, sla);
        }
        return new AsyncResult<>(sla);
    }

}
