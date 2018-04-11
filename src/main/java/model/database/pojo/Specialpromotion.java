package model.database.pojo;
// Generated Apr 11, 2018 12:54:19 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Specialpromotion generated by hbm2java
 */
public class Specialpromotion  implements java.io.Serializable {


     private Integer promotionId;
     private Users users;
     private String promotionName;
     private float discountPercent;
     private byte usageCount;
     private String description;
     private Date modifiedDate;
     private Set<Invoice> invoices = new HashSet<Invoice>(0);

    public Specialpromotion() {
    }

	
    public Specialpromotion(Users users, String promotionName, float discountPercent, byte usageCount, Date modifiedDate) {
        this.users = users;
        this.promotionName = promotionName;
        this.discountPercent = discountPercent;
        this.usageCount = usageCount;
        this.modifiedDate = modifiedDate;
    }
    public Specialpromotion(Users users, String promotionName, float discountPercent, byte usageCount, String description, Date modifiedDate, Set<Invoice> invoices) {
       this.users = users;
       this.promotionName = promotionName;
       this.discountPercent = discountPercent;
       this.usageCount = usageCount;
       this.description = description;
       this.modifiedDate = modifiedDate;
       this.invoices = invoices;
    }
   
    public Integer getPromotionId() {
        return this.promotionId;
    }
    
    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public String getPromotionName() {
        return this.promotionName;
    }
    
    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }
    public float getDiscountPercent() {
        return this.discountPercent;
    }
    
    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }
    public byte getUsageCount() {
        return this.usageCount;
    }
    
    public void setUsageCount(byte usageCount) {
        this.usageCount = usageCount;
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
    public Set<Invoice> getInvoices() {
        return this.invoices;
    }
    
    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }




}


