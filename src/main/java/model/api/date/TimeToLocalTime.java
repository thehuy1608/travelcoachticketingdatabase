/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author User
 */
public class TimeToLocalTime {

    public static LocalTime convert_date_to_local_time(Date time) {
        Instant instant = Instant.ofEpochMilli(time.getTime());
        LocalTime local_time = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
        return local_time;
    }
}
