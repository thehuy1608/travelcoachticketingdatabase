/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.ArrayList;
import java.util.List;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Driver;
import model.database.pojo.Trip;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class CoachDriverTripDAO {

    /**
     *Get all drivers of trip, include main driver and sub driver (optional) by Trip Object
     * @param trip Trip
     * @return List
     */
    public static List<Driver> get_driver_of_trip_list_by_trip(Trip trip) {
         List<Driver> driver_list = new ArrayList<>();
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql_driver = "SELECT coach_driver_trip.driverByFkDriverId FROM CoachDriverTrip coach_driver_trip WHERE coach_driver_trip.trip=:trip";
            Query query_driver = hibernate_session.createQuery(hql_driver);
            query_driver.setParameter("trip", trip);
            List<Driver> result_driver = query_driver.list();
            
            String hql_sub_driver = "SELECT coach_driver_trip.driverByFkSubDriverId FROM CoachDriverTrip coach_driver_trip WHERE coach_driver_trip.trip=:trip";
            Query query_sub_driver = hibernate_session.createQuery(hql_sub_driver);
            query_sub_driver.setParameter("trip", trip);
            List<Driver> result_sub_driver = query_sub_driver.list();
            
            if (!result_driver.isEmpty()) {
                driver_list.add(result_driver.get(0));
            }
            if (!result_sub_driver.isEmpty()) {
                driver_list.add(result_sub_driver.get(0));
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return driver_list;
    }

    /**
     *Get all drivers of trip, include main driver and sub driver (optional) by Trip ID
     * @param trip_id int
     * @return
     */
    public static List<Driver> get_driver_of_trip_list_by_trip_id(int trip_id) {
        List<Driver> driver_list = new ArrayList<>();
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql_driver = "SELECT coach_driver_trip.driverByFkDriverId FROM CoachDriverTrip coach_driver_trip WHERE coach_driver_trip.trip.tripId=:trip_id";
            Query query_driver = hibernate_session.createQuery(hql_driver);
            query_driver.setParameter("trip_id", trip_id);
            List<Driver> result_driver = query_driver.list();
            
            String hql_sub_driver = "SELECT coach_driver_trip.driverByFkSubDriverId FROM CoachDriverTrip coach_driver_trip WHERE coach_driver_trip.trip.tripId=:trip_id";
            Query query_sub_driver = hibernate_session.createQuery(hql_sub_driver);
            query_sub_driver.setParameter("trip_id", trip_id);
            List<Driver> result_sub_driver = query_sub_driver.list();
            
            if (!result_driver.isEmpty()) {
                driver_list.add(result_driver.get(0));
            }
            if (!result_sub_driver.isEmpty()) {
                driver_list.add(result_sub_driver.get(0));
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return driver_list;
    }

    public static void main(String[] args) {
        List<Driver> driver_list = get_driver_of_trip_list_by_trip_id(1);
        driver_list.forEach(driver -> {
            System.out.println(driver.getDriverName());
        });
    }
}
