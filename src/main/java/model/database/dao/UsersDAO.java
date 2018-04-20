/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import model.database.pojo.Users;
import model.database.hibernate.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class UsersDAO {

    /**
     *Get the user by its ID
     * @param user_id An integer number, must be greater than 0.
     * @return Object User
     */
    public static Users get_user_by_user_id(int user_id) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            Users user = (Users) hibernate_session.get(Users.class, user_id);
            return user;
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return null;
    }
}
