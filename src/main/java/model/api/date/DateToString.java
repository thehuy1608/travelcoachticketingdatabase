/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DateToString {
    public static String convert_date_to_string(Date date)  {
        String date_string = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date_string = format.format(date);
        } catch (Exception e) {
        }
        return date_string;
    }
    
    public static String convert_date_time_to_string(Date date)  {
        String date_string = null;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        try {
            date_string = format.format(date);
        } catch (Exception e) {
        }
        return date_string;
    }
}
