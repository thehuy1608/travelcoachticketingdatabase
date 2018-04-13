package model.database.pojo;
// Generated Apr 13, 2018 3:52:30 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cityordistrict generated by hbm2java
 */
public class Cityordistrict  implements java.io.Serializable {


     private Integer cityOrDistrictId;
     private Country country;
     private String cityOrDistrictName;
     private String postalCode;
     private Date modifiedDate;
     private Set<Station> stations = new HashSet<Station>(0);

    public Cityordistrict() {
    }

	
    public Cityordistrict(Country country, String cityOrDistrictName, String postalCode, Date modifiedDate) {
        this.country = country;
        this.cityOrDistrictName = cityOrDistrictName;
        this.postalCode = postalCode;
        this.modifiedDate = modifiedDate;
    }
    public Cityordistrict(Country country, String cityOrDistrictName, String postalCode, Date modifiedDate, Set<Station> stations) {
       this.country = country;
       this.cityOrDistrictName = cityOrDistrictName;
       this.postalCode = postalCode;
       this.modifiedDate = modifiedDate;
       this.stations = stations;
    }
   
    public Integer getCityOrDistrictId() {
        return this.cityOrDistrictId;
    }
    
    public void setCityOrDistrictId(Integer cityOrDistrictId) {
        this.cityOrDistrictId = cityOrDistrictId;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public String getCityOrDistrictName() {
        return this.cityOrDistrictName;
    }
    
    public void setCityOrDistrictName(String cityOrDistrictName) {
        this.cityOrDistrictName = cityOrDistrictName;
    }
    public String getPostalCode() {
        return this.postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public Set<Station> getStations() {
        return this.stations;
    }
    
    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }




}


