/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.dao;

import java.util.List;
import model.database.hibernate.HibernateUtil;
import model.database.pojo.Seat;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class SeatDAO {

    /**
     * Return Seat Object by coach id and seat number
     *
     * @param coach_id Integer
     * @param seat_number Byte
     * @return
     */
    public static Seat get_seat_by_coach_id_and_seat_number(int coach_id, byte seat_number) {
        Seat seat = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT seat FROM Seat seat WHERE seat.coach.coachId = :coach_id AND seat.seatNumber = :seat_number";
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("coach_id", coach_id);
            query.setParameter("seat_number", seat_number);
            List<Seat> result = query.list();
            if (!result.isEmpty()) {
                seat = result.get(0);
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return seat;
    }

    /**
     *Get the selected seat of a trip by the coach ID
     * @param coach_id
     * @return
     */
    public static List<Byte> get_all_selected_seat_of_trip_by_coach_id(int coach_id) {
        List<Byte> selected_seat_list = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            String hql = "SELECT seat.seatNumber FROM Seat seat WHERE seat.coach.coachId = :coach_id AND seat.seatStatus = :seat_status";
            byte seat_status = 1;
            Query query = hibernate_session.createQuery(hql);
            query.setParameter("coach_id", coach_id);
            query.setParameter("seat_status", seat_status);
            selected_seat_list = query.list();
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return selected_seat_list;
    }

    /**
     * Update seat object
     *
     * @param seat
     * @return
     */
    public static boolean update_seat(Seat seat) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            hibernate_session.saveOrUpdate(seat);
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
        List<Byte> selected_seat_list = get_all_selected_seat_of_trip_by_coach_id(1);
        selected_seat_list.forEach(seat ->{
            System.out.println(seat);
        });
    }
}
