/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.treetableview.datamodel;

import com.jfoenix.controls.JFXButton;

/**
 *
 * @author User
 */
public class TableSearchCustomerInfoDataModel {
    private final int index;
    private final String name;
    private final String phone_number;
    private final String trip_line_name;
    private final int number_of_tickets;
    private final String payment_status;
    private final JFXButton detail;
    private final JFXButton check_out;

    public TableSearchCustomerInfoDataModel(int index, String name, String phone_number, String trip_line_name, int number_of_tickets, String payment_status, JFXButton detail, JFXButton check_out) {
        this.index = index;
        this.name = name;
        this.phone_number = phone_number;
        this.trip_line_name = trip_line_name;
        this.number_of_tickets = number_of_tickets;
        this.payment_status = payment_status;
        this.detail = detail;
        this.check_out = check_out;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getTrip_line_name() {
        return trip_line_name;
    }

    public int getNumber_of_tickets() {
        return number_of_tickets;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public JFXButton getDetail() {
        return detail;
    }

    public JFXButton getCheck_out() {
        return check_out;
    }
    
    
}
