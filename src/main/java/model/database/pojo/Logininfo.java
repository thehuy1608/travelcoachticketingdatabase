package model.database.pojo;
// Generated Apr 15, 2018 6:35:56 PM by Hibernate Tools 4.3.1



/**
 * Logininfo generated by hbm2java
 */
public class Logininfo  implements java.io.Serializable {


     private int userId;
     private Users users;
     private String loginName;
     private byte[] loginPassword;

    public Logininfo() {
    }

    public Logininfo(Users users, String loginName, byte[] loginPassword) {
       this.users = users;
       this.loginName = loginName;
       this.loginPassword = loginPassword;
    }
   
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public String getLoginName() {
        return this.loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public byte[] getLoginPassword() {
        return this.loginPassword;
    }
    
    public void setLoginPassword(byte[] loginPassword) {
        this.loginPassword = loginPassword;
    }




}


