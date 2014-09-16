/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wichf.run;

import com.wichf.models.Appointment;
import com.wichf.models.TimeSlot;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Khalid
 */
public class TimeProblem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        TimeSlot ts;
        ts = new TimeSlot(2014, 1, 15, 8, 0, 17, 0);
        ArrayList<Long> timeList;

        timeList = ts.splitTimeIntoIntervals(10);

//        for (long l : timeList) {
//            System.out.println(dateFormat.format(l));
//        }

        Appointment appointment1 = new Appointment(2014, 1, 15, 10, 10, "DIA");
        Appointment appointment2 = new Appointment(2014, 1, 15, 8, 0, "DIA");
        ArrayList<Appointment> appointmentList = new ArrayList();
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        for (Appointment appointment : appointmentList) {
            System.out.println("Start Time: " + appointment.getAppointmentSlot().getStart().getTime());
            System.out.println("End Time: " + appointment.getAppointmentSlot().getEnd().getTime());
        }

        System.out.println(appointment1.getAppointmentSlot().getEnd().getTime());
        System.out.println(appointment2.getAppointmentSlot().getEnd().getTime());
        ArrayList<Long> excludeList;
        excludeList = ts.excludeBlockedIntervals(timeList, appointmentList);
//        for (long l : excludeList) {
//            System.out.println(dateFormat.format(l));
//        }     

    }
}
