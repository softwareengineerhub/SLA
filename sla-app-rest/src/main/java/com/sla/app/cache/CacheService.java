/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app.cache;

import com.sla.app.sla.service.SLA;

/**
 *
 * @author asusadmin
 */
public interface CacheService {

    public void put(String token, SLA sla);

    public SLA get(String token);

}
