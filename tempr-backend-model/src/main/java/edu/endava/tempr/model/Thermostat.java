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

    @Column(name = "user_id")
    private Long userId;

    @OneToMany
    @JoinTable(name = "thermostat_thermostatLog",
            joinColumns = @JoinColumn(name = "thermostat_token"),
            inverseJoinColumns = @JoinColumn(name = "token"))
    private List<ThermostatLog> thermostatLogList = new ArrayList<>();

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Short getConfigured() {
        return configured;
    }

    public void setConfigured(Short configured) {
        this.configured = configured;
    }

    public List<ThermostatLog> getThermostatLogList() {
        return thermostatLogList;
    }

    public void setThermostatLogList(List<ThermostatLog> thermostatLogList) {
        this.thermostatLogList = thermostatLogList;
    }

    public void addThermostatLog(ThermostatLog thermostatLog){
        if(!thermostatLogList.contains(thermostatLog)){
            thermostatLogList.add(thermostatLog);
        }
    }

    @Override
    public String toString() {
        return "Thermostat{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", configured='" + configured + '\'' +
                '}';
    }
}
