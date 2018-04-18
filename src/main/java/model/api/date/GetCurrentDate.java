/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.date;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author User
 */
public class GetCurrentDate {
    public static Date get_current_date() {
        LocalDate local_date = LocalDate.now();
        Date date = Date.from(local_date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
}
