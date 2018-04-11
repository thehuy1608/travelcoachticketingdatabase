/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.List;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Cityordistrict;
import model.database.pojo.Station;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class StationDAO {

    /**
     * Get the list of all Station objects in database
     *
     * @return
     */
    public static List<Station> get_station_list() {
        List<Station> station_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT station FROM Station station";
            Query query = hibernate_session.createQuery(hql);
            station_list = query.list();
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return station_list;
    }

    /**
     * Get the list of name of all Station objects in database
     *
     * @return
     */
    public static List<String> get_station_name_list() {
        List<String> station_name_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT stationName FROM Station";
            Query query = hibernate_session.createQuery(hql);
            station_name_list = query.list();
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return station_name_list;
    }

    /**
     * Get the list of address of all Station objects in database
     *
     * @return
     */
    public static List<String> get_station_address_list() {
        List<String> station_address_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT stationAddress FROM Station";
            Query query = hibernate_session.createQuery(hql);
            station_address_list = query.list();
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return station_address_list;
    }

    /**
     * Get Station object by its id
     *
     * @param station_id
     * @return Station object, null if the id doesn't exist
     */
    public static Station get_station_by_station_id(int station_id) {
        Station station = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            station = (Station) hibernate_session.get(Station.class, station_id);
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return station;
    }

    /**
     * Get Station object by its name
     *
     * @param station_name
     * @return Station object, null if the name doesn't exist or wrong naming
     * format
     */
    public static Station get_station_by_station_name(String station_name) {
        Station station = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT station FROM Station station WHERE station.stationName = :station_name";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("station_name", station_name);
            List<Station> result = query.list();
            if (!result.isEmpty()) {
                station = result.get(0);
            }
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return station;
    }

    /**
     *Get the list of all station in a city or district by the cityordistrict_id
     * @param city_or_district_id
     * @return 
     */
    public static List<Station> get_station_list_by_cityordistrict_id(int city_or_district_id) {
        List<Station> station_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT station FROM Station station WHERE station.cityordistrict.cityOrDistrictId=:city_or_district_id";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("city_or_district_id", city_or_district_id);
            station_list = query.list();
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return station_list;
    }
    
    /**
     *Get the list of all station in a city or district by the cityordistrict OBJECT
     * @param city_or_district
     * @return
     */
    public static List<Station> get_station_list_by_cityordistrict(Cityordistrict city_or_district) {
        List<Station> station_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT station FROM Station station WHERE station.cityordistrict=:city_or_district";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("city_or_district", city_or_district);
            station_list = query.list();
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return station_list;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Cityordistrict district = CityOrDistrictDAO.get_city_or_district_by_id(33);
        List<Station> station_list = get_station_list_by_cityordistrict(district);
        station_list.forEach((station) -> {
            System.out.println(station.getStationName());
        });
    }
}
