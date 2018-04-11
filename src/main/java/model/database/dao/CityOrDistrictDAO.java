/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.List;
import model.database.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class CityOrDistrictDAO {
    public static void main(String[] args) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT cityOrDistrictName FROM Cityordistrict";
            Query query = hibernate_session.createQuery(hql);
            List<String> list = query.list();
            list.forEach((name) -> {
                System.out.println(name);
            });
            hibernate_session.flush();
            hibernate_session.close();
        } catch (HibernateException e) {
        }
    }
}
