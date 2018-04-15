/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.List;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Customer;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class CustomerDAO {

    /**
     * Get Customer Object in database by the phone number
     *
     * @param phone_number
     * @return Object
     */
    public static Customer get_customer_by_phone_number(String phone_number) {
        Customer customer = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT customer from Customer customer WHERE customer.customerPhoneNumber=:phone_number";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("phone_number", phone_number);
            List<Customer> result = query.list();
            if (!result.isEmpty()) {
                customer = result.get(0);
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return customer;
    }

    /**
     * Get customer by name, return a list, cause customer's name maybe
     * duplicate
     *
     * @param name
     * @return List
     */
    public static List<Customer> get_customer_list_by_name(String name) {
        List<Customer> customer_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT customer from Customer customer WHERE customer.customerName=:name";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("name", name);
            customer_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return customer_list;
    }

    /**
     * Get all customer objects in database
     *
     * @return List
     */
    public static List<Customer> get_customer_list() {
        List<Customer> customer_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT customer from Customer customer";
            Query query = hibernate_session.createQuery(hql);
            customer_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return customer_list;
    }

    /**
     *Get the list of all phone numbers of customers in database
     * @return List
     */
    public static List<String> get_customer_phone_number_list() {
        List<String> customer_phone_number_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT customer.customerPhoneNumber FROM Customer customer";
            Query query = hibernate_session.createQuery(hql);
            customer_phone_number_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return customer_phone_number_list;
    }

    /**
     * Add new Customer to database
     *
     * @param customer
     * @return true if added successful, false otherwise
     */
    public static boolean add_customer(Customer customer) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            hibernate_session.save(customer);
            hibernate_session.getTransaction().commit();
        } catch (Exception e) {
            if (hibernate_session.getTransaction().isActive()) {
                hibernate_session.getTransaction().rollback();
                hibernate_session.flush();
                hibernate_session.close();
            }
        }
        hibernate_session.flush();
        hibernate_session.close();
        return false;
    }

    /**
     * Update existing customer in database
     *
     * @param customer
     * @return true if updated successful, false otherwise
     */
    public static boolean update_customer(Customer customer) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            hibernate_session.update(customer);
            hibernate_session.getTransaction().commit();
        } catch (Exception e) {
            if (hibernate_session.getTransaction().isActive()) {
                hibernate_session.getTransaction().rollback();
                hibernate_session.flush();
                hibernate_session.close();
            }
        }
        hibernate_session.flush();
        hibernate_session.close();
        return false;
    }

    public static void main(String[] args) {
//        Customer customer = get_customer_by_phone_number("0967543001");
//        System.out.println(customer.getCustomerName());.

//        List<Customer> customer_list = get_customer_list_by_name("Nguyễn Văn A");
//        System.out.println(customer_list.get(0).getCustomerPhoneNumber());
//        List<Customer> customer_list = get_customer_list();
//        customer_list.forEach(customer -> {
//            System.out.println(customer.getCustomerName());
//        });

        List<String> customer_phone = get_customer_phone_number_list();
        customer_phone.forEach(phone -> {
            System.out.println(phone);
        });
    }
}
