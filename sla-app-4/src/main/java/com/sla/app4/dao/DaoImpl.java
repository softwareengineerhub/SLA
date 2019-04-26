/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app4.dao;

import com.sla.app4.sla.service.SlaService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asusadmin
 */
public class DaoImpl implements Dao {

    private Map<String, SlaService.SLA> database;
    private List<SlaService.SLA> slas;
    private int tokensCount;

    public DaoImpl(int capacity) {
        tokensCount = capacity;
        slas = new ArrayList();
        for (int i = 0; i < tokensCount; i++) {
            slas.add(new SlaService.SLA("user" + (i + 1), (i + 1) * 10));
        }

        database = new HashMap<>();
        for (int i = 0; i < tokensCount; i++) {
            database.put("tokenUser" + (i + 1) + "A", slas.get(i));
            database.put("tokenUser" + (i + 1) + "B", slas.get(i));
        }
    }

    @Override
    public SlaService.SLA read(String token) {
        delay(300);
        return database.get(token);
    }

    private void delay(long delay) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= delay) {
        }
    }

}
