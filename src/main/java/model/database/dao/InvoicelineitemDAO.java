/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.List;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Invoice;
import model.database.pojo.Invoicelineitem;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class InvoicelineitemDAO {

    /**
     *Count number of invoice line items in an Invoice
     * @param invoice
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
}
