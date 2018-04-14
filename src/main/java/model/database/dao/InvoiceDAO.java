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
    public static List<Invoice> get_10_latest_invoice_by_phone_number(String phone_number) {
        List<Invoice> invoice_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT invoice FROM Invoice invoice WHERE invoice.customer.customerPhoneNumber=:phone_number ORDER BY invoice.invoiceDueDate DESC";
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
     *Get list of invoices of customer that has name like the name specified in parameter, the result maybe wrong.
     * @param name String
     * @return List
     */
    public static List<Invoice> get_invoice_list_by_name(String name) {
        List<Invoice> invoice_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT invoice FROM Invoice invoice WHERE invoice.customer.customerName LIKE :name ORDER BY invoice.invoiceStatus DESC";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("name", "%" + name + "%");
            invoice_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return invoice_list;
    }

    public static void main(String[] args) {
        List<Invoice> invoice_list = get_invoice_list_by_name("D");
        System.out.println(invoice_list.get(0).getInvoiceStatus());
    }
}
