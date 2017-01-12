package edu.endava.tempr.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltszabo on 13/12/2016.
 */
@Entity
@Table(name = "Thermostats")
public class Thermostat extends BaseEntity {

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "configured")
    private Short configured;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private User user;

    public Thermostat() {
        //EMPTY
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

    public Short getConfigured() {
        return configured;
    }

    public void setConfigured(Short configured) {
        this.configured = configured;
    }

    /*public List<ThermostatLog> getThermostatLogList() {
        return thermostatLogList;
    }

    public void setThermostatLogList(List<ThermostatLog> thermostatLogList) {
        this.thermostatLogList = thermostatLogList;
    }

    public void addThermostatLog(ThermostatLog thermostatLog){
        if(!thermostatLogList.contains(thermostatLog)){
            thermostatLogList.add(thermostatLog);
        }
    }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Thermostat{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", configured='" + configured + '\'' +
                '}';
    }
}
