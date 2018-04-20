/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.json.model;

/**
 *
 * @author User
 */
public class Metadata {
    private int user_id;
    private String username;
    
    public Metadata() {
        
    }

    public Metadata(int users_id, String username) {
        this.user_id = users_id;
        this.username = username;
    }

    public int get_users_id() {
        return user_id;
    }

    public String get_username() {
        return username;
    }  
}
