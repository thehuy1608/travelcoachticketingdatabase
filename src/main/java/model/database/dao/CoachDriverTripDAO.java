/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

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
    
    public static List<Driver> get_driver_of_trip_list_by_trip(Trip trip) {
        List<Driver> driver_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT driverByFkSubDriverId, driverByFkDriverId FROM CoachTripDriver coach_trip_driver WHERE coach_trip_driver.trip=:trip";
            Query query = hibernate_session.createQuery(hql);
            driver_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return driver_list;
    } 
    
    public static void main(String[] args) {
        
    }
}
