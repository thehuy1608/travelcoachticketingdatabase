/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.List;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Cityordistrict;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class CityOrDistrictDAO {

    /**
     *Get all Cityordistrict object in database
     * @return
     */
    public static List<Cityordistrict> get_city_or_district_list() {
        List<Cityordistrict> city_or_district_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT city_or_district FROM Cityordistrict city_or_district";
            Query query = hibernate_session.createQuery(hql);
            city_or_district_list = query.list();
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return city_or_district_list;

    }

    /**
     * Get the list of names of all cities and districts in database
     *
     * @return
     */
    public static List<String> get_city_or_district_name_list() {
        List<String> city_or_district_name_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT cityOrDistrictName FROM Cityordistrict";
            Query query = hibernate_session.createQuery(hql);
            city_or_district_name_list = query.list();
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return city_or_district_name_list;
    }

    /**
     * Get the Cityordistrict object by its ID.
     *
     * @param city_or_district_id the id of the Cityordisctrict, from 1 to 64
     * @return Cityordistrict object, null if the id doesn't exist.
     */
    public static Cityordistrict get_city_or_district_by_id(int city_or_district_id) {
        Cityordistrict city_or_district = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            city_or_district = (Cityordistrict) hibernate_session.get(Cityordistrict.class, city_or_district_id);
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return city_or_district;
    }

    /**
     * Get Cityordistrict object by its name, the name must be a valid city or
     * district in Vietnam. Notice "Thành phố Hồ Chí Minh" has different name
     * format than others.
     *
     * @param city_or_district_name The name of Cityordistrict in Vietnam, like
     * "An Giang", "Tiền Giang", "Hà Nội", "Đà Nẵng", "Thành phố Hồ Chí Minh"
     * @return Cityordistrict object, returns null if the name is incorrect, or
     * in wrong naming format.
     */
    public static Cityordistrict get_city_or_district_by_name(String city_or_district_name) {
        Cityordistrict city_or_district = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT city_or_district FROM Cityordistrict city_or_district WHERE city_or_district.cityOrDistrictName=:city_or_district_name";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("city_or_district_name", city_or_district_name);
            List<Cityordistrict> city_or_district_list = query.list();
            if (!city_or_district_list.isEmpty()) {
                city_or_district = city_or_district_list.get(0);
            }
        } catch (HibernateException e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return city_or_district;
    }
    
    /**
     * Get Cityordistrict ID by its name, the name must be a valid city or
     * district in Vietnam. Notice "Thành phố Hồ Chí Minh" has different name
     * format than others.
     * @param city_or_district_name
     * @return
     */
    public static int get_city_or_district_id_by_name(String city_or_district_name) {
        int city_or_district_id = 0;
        Cityordistrict city = get_city_or_district_by_name(city_or_district_name);
        if (city != null) {
            city_or_district_id = city.getCityOrDistrictId();
        }
        return city_or_district_id;
    }
    
    public static void main(String[] args) {
        Cityordistrict cod;
        String cod_name = "Bạc Liêu";
        cod = get_city_or_district_by_name(cod_name);
        System.out.println(cod.getCityOrDistrictName());
        System.out.println(cod.getPostalCode());
        System.out.println(cod.getCityOrDistrictId());
    }
}
