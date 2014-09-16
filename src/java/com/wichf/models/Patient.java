/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wichf.models;

/**
 *
 * @author PradeepSamuel
 */
public class Patient extends Person {

    private String healthCardNumber;
    private Doctor familyDoctor;

    public String getHealthCardNumber() {
        return healthCardNumber;
    }

    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }
    // TODO: Credit Card information is not yet relatedto the patient
}
