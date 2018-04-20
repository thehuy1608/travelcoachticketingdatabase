/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.List;
import model.api.security.Encryption;
import model.database.pojo.Users;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Logininfo;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class LoginInfoDAO {

    /**
     * Verify login information of user
     *
     * @param login_name String
     * @param login_password Byte[]
     * @return true if the login is matched, false otherwise.
     */
    public static boolean check_login(String login_name, byte[] login_password) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT COUNT(*) FROM Logininfo login_info WHERE login_info.loginName=:param_login_name AND login_info.loginPassword=:param_login_password";
            Query query = hibernate_session.createQuery(hql);
            query.setString("param_login_name", login_name);
            query.setBinary("param_login_password", login_password);
            List<Long> result_list = query.list();
            long result = result_list.get(0);
            return result > 0;
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return false;
    }

    /**
     * Get the user by user's login name and password
     *
     * @param login_name
     * @param login_password
     * @return
     */
    public static Users get_user_by_login_name_and_password(String login_name, byte[] login_password) {
        Users user = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT userId FROM Logininfo login_info WHERE login_info.loginName=:param_login_name AND login_info.loginPassword=:param_login_password";
            Query query = hibernate_session.createQuery(hql);
            query.setString("param_login_name", login_name);
            query.setBinary("param_login_password", login_password);
            List<Integer> result_list = query.list();
            int user_id = result_list.get(0);
            if (user_id > 0) {
                user = (Users) hibernate_session.get(Users.class, user_id);
            } else {
                return null;
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return user;
    }

    public static void main(String[] args) {
        try {
            Users user = UsersDAO.get_user_by_user_id(1);
            Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
            hibernate_session.beginTransaction();
            String username = "huynt1608";
            byte[] encrypted_login_password = Encryption.encrypt_AES("thehuy123");
            Logininfo login_info = new Logininfo(user, username, encrypted_login_password);
            hibernate_session.save(login_info);
            hibernate_session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
