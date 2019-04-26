/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app.controller;

import com.sla.app.business.processor.BusinessProcessor;
import com.sla.app.model.UsefulData;
import com.sla.app.throttling.service.ThrottlingService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asusadmin
 */
@RestController
public class ThrottlingController {

    @Autowired
    private ThrottlingService throttlingService;
    @Autowired
    private BusinessProcessor businessProcessor;

    @RequestMapping(value = "/throttling", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UsefulData get(@RequestParam(value = "userToken", required = false) String userToken) {
        if (throttlingService.isRequestAllowed(Optional.ofNullable(userToken))) {
            return businessProcessor.process();
        }
        return new UsefulData();
    }

}
