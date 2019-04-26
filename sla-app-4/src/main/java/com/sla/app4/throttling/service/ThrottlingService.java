/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app4.throttling.service;

import java.util.Optional;

/**
 *
 * @author asusadmin
 */
public interface ThrottlingService {

    boolean isRequestAllowed(Optional<String> token);

}
