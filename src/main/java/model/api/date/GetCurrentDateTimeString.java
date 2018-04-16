/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author User
 */
public class GetCurrentDateTimeString {
    public static String get_current_datetime_string() {
        String current_datetime_string;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        current_datetime_string = dtf.format(now);
        return current_datetime_string;
    }
    
    public static void main(String[] args) {
        System.out.println(get_current_datetime_string());
    }
}
