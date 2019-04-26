/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app4.dao;

import com.sla.app4.sla.service.SlaService.SLA;

/**
 *
 * @author asusadmin
 */
public interface Dao {

    public SLA read(String token);

}
