/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.ArrayList;
import java.util.List;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Invoice;
import model.database.pojo.Invoicelineitem;
import org.hibernate.Query;
import org.hibernate.Session;
import model.database.pojo.*;

/**
 *
 * @author User
 */
public class InvoicelineitemDAO {

    /**
     * Count number of invoice line items in an Invoice by Invoice Object
     *
     * @param invoice Invoice
     * @return
     */
    public static int count_invoice_line_items(Invoice invoice) {
        int number_of_items = -1;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line_item FROM Invoicelineitem line_item WHERE line_item.invoice=:invoice";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("invoice", invoice);
            List<Invoicelineitem> result = query.list();
            number_of_items = result.size();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return number_of_items;
    }

    /**
     * Count number of invoice line items in an Invoice by invoice ID
     *
     * @param invoice_id int
     * @return
     */
    public static int count_invoice_line_items(int invoice_id) {
        int number_of_items = -1;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line_item FROM Invoicelineitem line_item WHERE line_item.invoice.invoiceId=:invoice_id";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("invoice_id", invoice_id);
            List<Invoicelineitem> result = query.list();
            number_of_items = result.size();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return number_of_items;
    }

    /**
     * Get list of items in an invoice by the Invoice Object
     *
     * @param invoice Invoice
     * @return
     */
    public static List<Invoicelineitem> get_invoice_line_items_by_invoice(Invoice invoice) {
        List<Invoicelineitem> items_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line_item FROM Invoicelineitem line_item WHERE line_item.invoice=:invoice";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("invoice", invoice);
            items_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return items_list;
    }

    /**
     * Get list of items in an invoice by the Invoice ID
     *
     * @param invoice_id
     * @return
     */
    public static List<Invoicelineitem> get_invoice_line_items_by_invoice_id(int invoice_id) {
        List<Invoicelineitem> items_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT line_item FROM Invoicelineitem line_item WHERE line_item.invoice.invoiceId=:invoice_id";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("invoice_id", invoice_id);
            items_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return items_list;
    }

    public static boolean add_invoice_line_item(Invoicelineitem item) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            hibernate_session.save(item);
            hibernate_session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (hibernate_session.getTransaction().isActive()) {
                hibernate_session.getTransaction().rollback();
            }
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return false;
    }

    public static boolean delete_invoice_line_item(Invoicelineitem item) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            hibernate_session.delete(item);
            hibernate_session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (hibernate_session.getTransaction().isActive()) {
                hibernate_session.getTransaction().rollback();
            }
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return false;
    }

    public static void main(String[] args) {
        System.out.println(get_invoice_line_items_by_invoice_id(1));
    }
}
