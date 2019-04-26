/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app3.cache;

import com.sla.app3.sla.service.SlaService.SLA;

/**
 *
 * @author asusadmin
 */
public interface CacheService {

    public void put(String token, SLA sla);

    public SLA get(String token);

}
