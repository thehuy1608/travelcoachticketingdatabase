/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.Date;
import java.util.List;
import model.api.date.StringToDate;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Line;
import model.database.pojo.Schedule;
import model.database.pojo.Trip;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class TripDAO {

    /**
     *Get list of trips by line
     * @param line
     * @return List
     */
    public static List<Trip> get_trip_list_by_line(Line line) {
        List<Trip> trip_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT trip FROM Trip trip WHERE trip.line=:line";
            Query query  = hibernate_session.createQuery(hql);
            trip_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
            hibernate_session.close();
        return trip_list;
    }
    
    public static List<Trip> get_trip_list_by_start_date(String start_date_string) {
        List<Trip> trip_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT trip FROM Trip trip WHERE trip.schedule.startDate=:start_date";
            Query query  = hibernate_session.createQuery(hql);
            Date start_date = StringToDate.convert_string_to_date(start_date_string);
            query.setParameter("start_date", start_date);
            trip_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
            hibernate_session.close();
        return trip_list;
    }
    
    public static void main(String[] args) {
        List<Trip> trip_list = get_trip_list_by_start_date("12-07-2018");
        System.out.println(trip_list.get(0).getLine().getLineName());
    }
}
