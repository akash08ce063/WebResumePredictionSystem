/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wichf.models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PradeepSamuel
 */
public class Staff extends Person{

    private Date joinedDate;
    private ArrayList languages;

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public ArrayList getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList languages) {
        this.languages = languages;
    }
    
    
}
