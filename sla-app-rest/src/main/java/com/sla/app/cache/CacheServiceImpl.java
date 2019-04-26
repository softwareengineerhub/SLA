/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app.cache;

import com.sla.app.sla.service.SLA;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author asusadmin
 */
@Service
public class CacheServiceImpl implements CacheService {

    private Map<String, SLA> map;

    public CacheServiceImpl() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void put(String token, SLA sla) {
        map.put(token, sla);
    }

    @Override
    public SLA get(String token) {
        return map.get(token);
    }

}
