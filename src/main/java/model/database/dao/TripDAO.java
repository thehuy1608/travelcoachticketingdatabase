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
import model.database.pojo.Trip;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class TripDAO {

    /**
     * Get list of trips by line
     *
     * @param line
     * @return List
     */
    public static List<Trip> get_trip_list_by_line(Line line) {
        List<Trip> trip_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT trip FROM Trip trip WHERE trip.line=:line";
            Query query = hibernate_session.createQuery(hql);
            trip_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return trip_list;
    }

    /**
     * Get all trips available in a day specified in parameter, notice that the
     * date in parameter must have the format "dd-MM-yyyy"
     *
     * @param start_date_string
     * @return
     */
    public static List<Trip> get_trip_list_by_start_date(String start_date_string) {
        List<Trip> trip_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT trip FROM Trip trip WHERE trip.schedule.startDate=:start_date";
            Query query = hibernate_session.createQuery(hql);
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

    /**
     *Get trip ID by trip Name
     * @param trip_name
     * @return
     */
    public static int get_trip_id_by_trip_name(String trip_name) {
        int trip_id = 0;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT trip.tripId FROM Trip trip WHERE trip.tripName = :trip_name";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("trip_name", trip_name);
            List<Integer> result = query.list();
            if (!result.isEmpty()) {
                trip_id = result.get(0);
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return trip_id;
    }
    
    /**
     *Get trip by its ID
     * @param trip_id
     * @return
     */
    public static Trip get_trip_by_trip_id(int trip_id) {
        Trip trip = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            trip = (Trip) hibernate_session.get(Trip.class, trip_id);
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return trip;
    }
    

    public static void main(String[] args) {
        List<Trip> trip_list = get_trip_list_by_start_date("12-07-2018");
        System.out.println(trip_list.get(0).getLine().getLineName());
    }
}
