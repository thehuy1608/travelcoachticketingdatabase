package model.database.pojo;
// Generated Apr 15, 2018 6:35:56 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * CoachDriverTrip generated by hbm2java
 */
public class CoachDriverTrip  implements java.io.Serializable {


     private Integer id;
     private Coach coach;
     private Driver driverByFkSubDriverId;
     private Driver driverByFkDriverId;
     private Trip trip;
     private String description;
     private Date modifiedDate;

    public CoachDriverTrip() {
    }

	
    public CoachDriverTrip(Coach coach, Driver driverByFkDriverId, Trip trip, String description, Date modifiedDate) {
        this.coach = coach;
        this.driverByFkDriverId = driverByFkDriverId;
        this.trip = trip;
        this.description = description;
        this.modifiedDate = modifiedDate;
    }
    public CoachDriverTrip(Coach coach, Driver driverByFkSubDriverId, Driver driverByFkDriverId, Trip trip, String description, Date modifiedDate) {
       this.coach = coach;
       this.driverByFkSubDriverId = driverByFkSubDriverId;
       this.driverByFkDriverId = driverByFkDriverId;
       this.trip = trip;
       this.description = description;
       this.modifiedDate = modifiedDate;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Coach getCoach() {
        return this.coach;
    }
    
    public void setCoach(Coach coach) {
        this.coach = coach;
    }
    public Driver getDriverByFkSubDriverId() {
        return this.driverByFkSubDriverId;
    }
    
    public void setDriverByFkSubDriverId(Driver driverByFkSubDriverId) {
        this.driverByFkSubDriverId = driverByFkSubDriverId;
    }
    public Driver getDriverByFkDriverId() {
        return this.driverByFkDriverId;
    }
    
    public void setDriverByFkDriverId(Driver driverByFkDriverId) {
        this.driverByFkDriverId = driverByFkDriverId;
    }
    public Trip getTrip() {
        return this.trip;
    }
    
    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }




}


