/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app.sla.service;

import java.util.concurrent.Future;

/**
 *
 * @author asusadmin
 */
public interface SlaService {

    Future<SLA> getSlaByToken(String token);
}
