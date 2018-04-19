/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.ArrayList;
import java.util.List;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Cityordistrict;
import model.database.pojo.Line;
import model.database.pojo.Station;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class LineDAO {

    /**
     * Get the list of lines between two station: departure_station and
     * destination_station
     *
     * @param departure_station Station
     * @param destination_station Station
     * @return Line Object, returns null if their is no line between two station
     */
    public static List<Line> get_all_line_by_departure_and_destination_station(Station departure_station, Station destination_station) {
        List<Line> line_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line FROM Line line WHERE line.stationByDepartureStationId=:departure_station AND line.stationByDestinationStationId=:destination_station";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("departure_station", departure_station);
            query.setParameter("destination_station", destination_station);
            line_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return line_list;
    }

    /**
     * Get the list of lines between two station: departure_station and
     * destination_station by two station_id
     *
     * @param departure_station_id int
     * @param destination_station_id int
     * @return
     */
    public static List<Line> get_all_line_by_departure_and_destination_station_id(int departure_station_id, int destination_station_id) {
        List<Line> line_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line FROM Line line WHERE line.stationByDepartureStationId.stationId=:departure_station_id AND line.stationByDestinationStationId.stationId=:destination_station_id";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("departure_station_id", departure_station_id);
            query.setParameter("destination_station_id", destination_station_id);
            line_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return line_list;
    }

    /**
     * Get the list of lines between two city or district: departure_city and
     * destination_city by two Cityordistrict Object
     *
     * @param departure_city Cityordistrict
     * @param destination_city Cityordistrict
     * @return
     */
    public static List<Line> get_all_line_by_departure_and_destination_cityordistrict(Cityordistrict departure_city, Cityordistrict destination_city) {
        List<Line> line_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line FROM Line line WHERE line.stationByDepartureStationId.cityordistrict=:departure_city AND line.stationByDestinationStationId.cityordistrict=:destination_city";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("departure_city", departure_city);
            query.setParameter("destination_city", destination_city);
            line_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return line_list;
    }

    /**
     * Get the list of lines between two city or district by the cityordistrict
     * ID: departure_city_id and destination_city_id
     *
     * @param departure_city_id int
     * @param destination_city_id int
     * @return
     */
    public static List<Line> get_all_line_by_departure_and_destination_cityordistrict_id(int departure_city_id, int destination_city_id) {
        List<Line> line_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line FROM Line line WHERE line.stationByDepartureStationId.cityordistrict.cityOrDistrictId=:departure_city_id AND line.stationByDestinationStationId.cityordistrict.cityOrDistrictId=:destination_city_id";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("departure_city_id", departure_city_id);
            query.setParameter("destination_city_id", destination_city_id);
            line_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return line_list;
    }

    /**
     * Get the list of all line that has departure station equals to
     * departure_station in parameter
     *
     * @param departure_station Station
     * @return
     */
    public static List<Line> get_all_line_by_departure_station(Station departure_station) {
        List<Line> line_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line FROM Line line WHERE line.stationByDepartureStationId=:departure_station";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("departure_station", departure_station);
            line_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return line_list;
    }

    /**
     * Get the list of all line that has departure station ID equals to
     * departure_station_id in parameter
     *
     * @param departure_station_id
     * @return
     */
    public static List<Line> get_all_line_by_departure_station_id(int departure_station_id) {
        List<Line> line_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line FROM Line line WHERE line.stationByDepartureStationId.stationId=:departure_station_id";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("departure_station_id", departure_station_id);
            line_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return line_list;
    }

    /**
     * Get the list of all line that has destination station equals to
     * destination_station in parameter
     *
     * @param destination_station
     * @return
     */
    public static List<Line> get_all_line_by_destination_station(Station destination_station) {
        List<Line> line_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line FROM Line line WHERE line.stationByDestinationStationId=:destination_station";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("destination_station", destination_station);
            line_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return line_list;
    }

    /**
     * Get the list of all line that has destination station ID equals to
     * destination_station_id in parameter
     *
     * @param destination_station_id
     * @return
     */
    public static List<Line> get_all_line_by_destination_station_id(int destination_station_id) {
        List<Line> line_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line FROM Line line WHERE line.stationByDestinationStationId.stationId=:destination_station_id";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("destination_station_id", destination_station_id);
            line_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return line_list;
    }

    /**
     *Get the ID of the line by its name
     * @param line_name
     * @return
     */
    public static int get_line_id_by_line_name(String line_name) {
        int line_id = 0;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line.lineId FROM Line line WHERE line.lineName = :line_name";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("line_name", line_name);
            List<Integer> result = query.list();
            if (!result.isEmpty()) {
                line_id = result.get(0);
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return line_id;
    }
    
     public static Line get_line_by_line_id(int line_id) {
         Line line = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
           line = (Line) hibernate_session.get(Line.class, line_id);
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return line;
    }
    
    public static List<Line> get_line_list_by_trip_id_list(List<Integer> trip_id_list) {
        List<Line> line_list = new ArrayList<>();
        trip_id_list.forEach(trip_id -> {
            Line line = TripDAO.get_trip_by_trip_id(trip_id).getLine();
            if (!line_list.contains(line)) {
                line_list.add(line);
            }
        });
        return line_list;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        Station departure_station = StationDAO.get_station_by_station_id(3);
//        Station destination_station = StationDAO.get_station_by_station_id(9);
//        List<Line> line_list = get_all_line_by_departure_and_destination_station(departure_station, destination_station);
//        line_list.forEach((line) -> {
//            System.out.println(line.getLineName());
//        });

//        List<Line> line_list = get_all_line_by_departure_and_destination_station_id(3, 9);
//        line_list.forEach((line) -> {
//            System.out.println(line.getLineName());
//        });
//        Cityordistrict departure_city = CityOrDistrictDAO.get_city_or_district_by_id(3);
//        Cityordistrict destination_city = CityOrDistrictDAO.get_city_or_district_by_id(9);
//        List<Line> line_list = get_all_line_by_departure_and_destination_cityordistrict(departure_city, destination_city);
//        line_list.forEach((line) -> {
//            System.out.println(line.getLineName());
//        });
//        List<Line> line_list = get_all_line_by_departure_and_destination_cityordistrict_id(3, 9);
//        line_list.forEach((line) -> {
//            System.out.println(line.getLineName());
//        });
//        Station departure_station = StationDAO.get_station_by_station_id(33);
//        List<Line> line_list = get_all_line_by_departure_station(departure_station);
//        line_list.forEach((line) -> {
//            System.out.println(line.getLineName());
//        });
//        List<Line> line_list = get_all_line_by_departure_station_id(33);
//        line_list.forEach((line) -> {
//            System.out.println(line.getLineName());
//        });
//        Station destination_station = StationDAO.get_station_by_station_id(33);
//        List<Line> line_list = get_all_line_by_destination_station(destination_station);
//        line_list.forEach((line) -> {
//            System.out.println(line.getLineName());
////        });
//        List<Line> line_list = get_all_line_by_destination_station_id(33);
//        line_list.forEach((line) -> {
//            System.out.println(line.getLineName());
//        });
        
    }
}
