/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.List;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Invoice;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class InvoiceDAO {

    /**
     * Get 10 latest invoice of customer that has the phone number specified in
     * parameter
     *
     * @param phone_number String
     * @return List
     */
    public static List<Invoice> get_latest_invoice_by_phone_number(String phone_number) {
        List<Invoice> invoice_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT invoice FROM Invoice invoice WHERE invoice.customer.customerPhoneNumber=:phone_number ORDER BY invoice.modifiedDate DESC";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("phone_number", phone_number);
            query.setMaxResults(10);
            invoice_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return invoice_list;
    }

    /**
     * Get list of invoices of customer that has name like the name specified in
     * parameter, the result maybe wrong.
     *
     * @param name String
     * @return List
     */
    public static List<Invoice> get_invoice_list_by_name(String name) {
        List<Invoice> invoice_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            if (name.equals("") || name.isEmpty()) {
                String hql = "SELECT invoice FROM Invoice invoice ORDER BY invoice.invoiceStatus DESC";
                Query query = hibernate_session.createQuery(hql);
                invoice_list = query.list();
            } else {
                name = name.trim();
                String hql = "SELECT invoice FROM Invoice invoice WHERE invoice.customer.customerName LIKE :name ORDER BY invoice.invoiceStatus DESC";
                Query query = hibernate_session.createQuery(hql);
                query.setParameter("name", "%" + name + "%");
                invoice_list = query.list();
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return invoice_list;
    }

    /**
     * Return the total price of invoice
     *
     * @param invoice
     * @return
     */
    public static double get_total_price(Invoice invoice) {
        double total_price = 0;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT SUM(invoice_line_item.price) FROM Invoicelineitem invoice_line_item WHERE invoice_line_item.invoice=:invoice";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("invoice", invoice);
            List<Double> result = query.list();
            if (!result.isEmpty()) {
                total_price = result.get(0);
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return total_price;
    }

    /**
     * Get an invoice object by the id input
     *
     * @param invoice_id
     * @return
     */
    public static Invoice get_invoice_by_id(int invoice_id) {
        Invoice invoice = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            invoice = (Invoice) hibernate_session.get(Invoice.class, invoice_id);
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return invoice;
    }

    /**
     * Add a new invoice to database
     *
     * @param invoice
     * @return true if added successfully, false otherwise
     */
    public static boolean add_invoice(Invoice invoice) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            hibernate_session.save(invoice);
            hibernate_session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (hibernate_session.getTransaction().isActive()) {
                hibernate_session.getTransaction().rollback();
            }
            hibernate_session.flush();
            hibernate_session.close();
            return false;
        }
    }

    /**
     * Update an existing invoice in database
     *
     * @param invoice
     * @return true if added successfully, false otherwise
     */
    public static boolean update_invoice(Invoice invoice) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            hibernate_session.update(invoice);
            hibernate_session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (hibernate_session.getTransaction().isActive()) {
                hibernate_session.getTransaction().rollback();
            }
            hibernate_session.flush();
            hibernate_session.close();
            return false;
        }
    }

    public static void main(String[] args) {
        List<Invoice> invoice_list = get_latest_invoice_by_phone_number("0967543010");
        System.out.println(get_invoice_by_id(1).getInvoiceStatus());
    }
}
