package org.endava.tempr.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class UserEntry {
    @Id
    @Column(name="userID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;

    @Column(name="username")
    private String userName;

    @Column(name="userpassword")
    private String userPassword;

    public UserEntry(){};

    public UserEntry(long userId){
        this.userId = userId;
    };

    public UserEntry(String userName, String userPassword){
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName(){

        return userName;
    }

    public String getUserPassword(){

        return userPassword;
    }

    public Long getId(){

        return userId;
    }

    public void setUserName(String userName){

        this.userName = userName;
    }

    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }
}
