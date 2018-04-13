package model.database.pojo;
// Generated Apr 13, 2018 3:52:30 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Station generated by hbm2java
 */
public class Station  implements java.io.Serializable {


     private Integer stationId;
     private Cityordistrict cityordistrict;
     private String stationName;
     private String stationAddress;
     private String stationHotline;
     private Date modifiedDate;
     private Set<Line> linesForDepartureStationId = new HashSet<Line>(0);
     private Set<Line> linesForDestinationStationId = new HashSet<Line>(0);

    public Station() {
    }

	
    public Station(Cityordistrict cityordistrict, String stationName, String stationAddress, String stationHotline, Date modifiedDate) {
        this.cityordistrict = cityordistrict;
        this.stationName = stationName;
        this.stationAddress = stationAddress;
        this.stationHotline = stationHotline;
        this.modifiedDate = modifiedDate;
    }
    public Station(Cityordistrict cityordistrict, String stationName, String stationAddress, String stationHotline, Date modifiedDate, Set<Line> linesForDepartureStationId, Set<Line> linesForDestinationStationId) {
       this.cityordistrict = cityordistrict;
       this.stationName = stationName;
       this.stationAddress = stationAddress;
       this.stationHotline = stationHotline;
       this.modifiedDate = modifiedDate;
       this.linesForDepartureStationId = linesForDepartureStationId;
       this.linesForDestinationStationId = linesForDestinationStationId;
    }
   
    public Integer getStationId() {
        return this.stationId;
    }
    
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }
    public Cityordistrict getCityordistrict() {
        return this.cityordistrict;
    }
    
    public void setCityordistrict(Cityordistrict cityordistrict) {
        this.cityordistrict = cityordistrict;
    }
    public String getStationName() {
        return this.stationName;
    }
    
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    public String getStationAddress() {
        return this.stationAddress;
    }
    
    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }
    public String getStationHotline() {
        return this.stationHotline;
    }
    
    public void setStationHotline(String stationHotline) {
        this.stationHotline = stationHotline;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public Set<Line> getLinesForDepartureStationId() {
        return this.linesForDepartureStationId;
    }
    
    public void setLinesForDepartureStationId(Set<Line> linesForDepartureStationId) {
        this.linesForDepartureStationId = linesForDepartureStationId;
    }
    public Set<Line> getLinesForDestinationStationId() {
        return this.linesForDestinationStationId;
    }
    
    public void setLinesForDestinationStationId(Set<Line> linesForDestinationStationId) {
        this.linesForDestinationStationId = linesForDestinationStationId;
    }




}


