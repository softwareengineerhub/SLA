/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sla.app.business.processor;

import com.sla.app.model.UsefulData;
import org.springframework.stereotype.Component;

/**
 *
 * @author asusadmin
 */
@Component
public class BusinessProcessorImpl implements BusinessProcessor {

    @Override
    public UsefulData process() {
        UsefulData usefulData = new UsefulData();
        usefulData.setName(String.format("Computer %s", System.currentTimeMillis()));
        usefulData.setYearOfManufacture(2019);
        return usefulData;
    }

}
