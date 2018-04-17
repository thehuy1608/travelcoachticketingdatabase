/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.List;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Ticket;
import model.database.pojo.Trip;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class TicketDAO {

    /**
     *Get ticket by seat number and the trip it belongs to.
     * @param seat_number
     * @param trip
     * @return
     */
    public static Ticket get_ticket_by_seat_number_and_trip(byte seat_number, Trip trip) {
        Ticket ticket = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT ticket FROM Ticket ticket WHERE ticket.ticketSeatNumber = :seat_number and ticket.trip = :trip";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("seat_number", seat_number);
            query.setParameter("trip", trip);
            List<Ticket> result = query.list();
            if (!result.isEmpty()) {
                ticket = result.get(0);
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return ticket;
    }
    
    /**
     *Get ticket price by trip id
     * @param trip_id
     * @return
     */
    public static double get_ticket_price_by_trip_id(int trip_id) {
        double ticket_price = 0;
        Trip trip = TripDAO.get_trip_by_trip_id(trip_id);
        byte seat_number = 1;
        Ticket ticket = get_ticket_by_seat_number_and_trip(seat_number, trip);
        ticket_price = ticket.getTicketPrice();
        return ticket_price;
    }
    
    public static void main(String[] args) {
//        Trip trip = TripDAO.get_trip_by_trip_id(1);
        byte seat_number = 1;
        System.out.println(get_ticket_price_by_trip_id(1));
    }
}
