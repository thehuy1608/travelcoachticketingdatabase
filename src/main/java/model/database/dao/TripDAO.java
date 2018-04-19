/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * Get trip ID by trip Name
     *
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
     * Get trip by trip name
     *
     * @param trip_name
     * @return
     */
    public static Trip get_trip_by_trip_name(String trip_name) {
        Trip trip = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT trip FROM Trip trip WHERE trip.tripName = :trip_name";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("trip_name", trip_name);
            List<Trip> result = query.list();
            if (!result.isEmpty()) {
                trip = result.get(0);
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return trip;
    }

    /**
     * Get trip by its ID
     *
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

    public static List<Integer> get_trip_id_list_by_city_departure_and_city_destination_id_and_start_date(int city_departure_id, int city_destination_id, String start_date_string) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start_date = null;
        try {
            start_date = sdf.parse(start_date_string);
        } catch (ParseException ex) {
            Logger.getLogger(TripDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Integer> trip_id_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT trip.tripId FROM Trip trip WHERE trip.schedule.startDate = :start_date AND trip.line.stationByDepartureStationId.cityordistrict.cityOrDistrictId = :city_departure_id AND trip.line.stationByDestinationStationId.cityordistrict.cityOrDistrictId = :city_destination_id";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("start_date", start_date);
            query.setParameter("city_departure_id", city_departure_id);
            query.setParameter("city_destination_id", city_destination_id);
            trip_id_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return trip_id_list;
    }

    public static List<Trip> get_trip_list_by_line_id_and_start_date_string(int line_id, String start_date_string) {
        List<Trip> trip_list = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start_date = null;
        try {
            start_date = sdf.parse(start_date_string);
        } catch (ParseException ex) {
            Logger.getLogger(TripDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT trip FROM Trip trip WHERE trip.line.lineId = :line_id and trip.schedule.startDate=:start_date";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("line_id", line_id);
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
        List<Trip> trip_list = get_trip_list_by_line_id_and_start_date_string(2221, "2018-07-12");
        trip_list.forEach(trip -> {
            System.out.println(trip.getTripId());
        });
    }
}
