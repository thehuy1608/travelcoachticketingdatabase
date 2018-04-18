/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.api.date.DateToString;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Schedule;
import model.database.pojo.Trip;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class ScheduleDAO {

    /**
     * Get Schedule object by trip ID
     *
     * @param trip_id
     * @return
     */
    public static Schedule get_schedule_by_trip_id(int trip_id) {
        Schedule schedule = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            schedule = (Schedule) hibernate_session.get(Schedule.class, trip_id);
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return schedule;
    }

    /**
     * Get Date Object of Start Date by trip ID
     *
     * @param trip_id
     * @return
     */
    public static Date get_schedule_start_date(int trip_id) {
        Date start_date = null;
        Schedule schedule = get_schedule_by_trip_id(trip_id);
        start_date = schedule.getStartDate();
        return start_date;
    }

    /**
     * Get Date Object of End Date by trip ID
     *
     * @param trip_id
     * @return
     */
    public static Date get_schedule_end_date(int trip_id) {
        Date end_date = null;
        Schedule schedule = get_schedule_by_trip_id(trip_id);
        end_date = schedule.getEndDate();
        return end_date;
    }

    /**
     * Get Date Object of Start Time by trip ID
     *
     * @param trip_id
     * @return
     */
    public static Date get_schedule_start_time(int trip_id) {
        Date start_time = null;
        Schedule schedule = get_schedule_by_trip_id(trip_id);
        start_time = schedule.getStartTime();
        return start_time;
    }

    /**
     * Get Date Object of End Time by trip ID
     *
     * @param trip_id
     * @return
     */
    public static Date get_schedule_end_time(int trip_id) {
        Date end_time = null;
        Schedule schedule = get_schedule_by_trip_id(trip_id);
        end_time = schedule.getEndTime();
        return end_time;
    }

    /**
     * Get String of Start Date by trip ID
     *
     * @param trip_id
     * @return
     */
    public static String get_schedule_start_date_string(int trip_id) {
        Date start_date = get_schedule_start_date(trip_id);
        String start_date_string = DateToString.convert_date_to_string(start_date);
        return start_date_string;
    }

    /**
     * Get String of End Date by trip ID
     *
     * @param trip_id
     * @return
     */
    public static String get_schedule_end_date_string(int trip_id) {
        Date end_date = get_schedule_end_date(trip_id);
        String end_date_string = DateToString.convert_date_to_string(end_date);
        return end_date_string;
    }

    /**
     * Get String of Start Time by trip ID
     *
     * @param trip_id
     * @return
     */
    public static String get_schedule_start_time_string(int trip_id) {
        Date start_time = get_schedule_start_time(trip_id);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String start_time_string = format.format(start_time);
        return start_time_string;
    }

    /**
     * Get String of End Time by trip ID
     *
     * @param trip_id
     * @return
     */
    public static String get_schedule_end_time_string(int trip_id) {
        Date end_time = get_schedule_end_time(trip_id);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String end_time_string = format.format(end_time);
        return end_time_string;
    }

    public static List<Schedule> get_schedule_list_by_line_id_and_start_date_string(int line_id, String start_date_string) {
        List<Schedule> schedule_list = new ArrayList<>();
        List<Trip> trip_list = TripDAO.get_trip_list_by_line_id_and_start_date_string(line_id, start_date_string);
        trip_list.forEach(trip -> {
            Schedule schedule = trip.getSchedule();
            if ( schedule != null) {
                schedule_list.add(schedule);
            }
        });
        return schedule_list;
    }

    public static void main(String[] args) {
        List<Schedule> schedule_list = get_schedule_list_by_line_id_and_start_date_string(2221, "2018-07-12");
        System.out.println(schedule_list.size());
    }
}
