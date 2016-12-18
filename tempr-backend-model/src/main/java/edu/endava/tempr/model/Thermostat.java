package edu.endava.tempr.model;

import javax.persistence.*;

/**
 * Created by zsoltszabo on 13/12/2016.
 */
@Entity
@Table(name = "Thermostats")
public class Thermostat extends BaseEntity {
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "name")
    private String name;

    //@Column(name = "user_id", nullable = false)
    //private Long userId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Thermostat(){}

    public Thermostat(String token, String name, User user){
        this.name = name;
        this.token = token;
        //this.userId = userId;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Long getUserId() {
        return 3L;
        //return userId;
    }

    public void setUserId(Long userId) {
        //this.userId = userId;
    }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
