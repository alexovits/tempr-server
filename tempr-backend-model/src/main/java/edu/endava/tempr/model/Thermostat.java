package edu.endava.tempr.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltszabo on 13/12/2016.
 */
@Entity
@Table(name = "Thermostats", indexes={@Index(name="Thermostat_Token_Index", columnList = "token")})
public class Thermostat extends BaseEntity {

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "configured")
    private Short configured;

    @Column(name = "name")
    private String name;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "thermostat", fetch = FetchType.EAGER)
    private List<HeatingCircuit> heatingCircuitList = new ArrayList<>();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<HeatingCircuit> getHeatingCircuitList() {
        return heatingCircuitList;
    }

    public void setHeatingCircuitList(List<HeatingCircuit> heatingCircuitList) {
        this.heatingCircuitList = new ArrayList<>();
        heatingCircuitList.forEach(hc -> this.heatingCircuitList.add(hc));
    }

    public void addHeatingCircuit(HeatingCircuit hc){
        if(!heatingCircuitList.contains(hc)){
            heatingCircuitList.add(hc);
        }
    }

    @Override
    public String toString() {
        return String.format("Thermostat{ name=%1$s, token=%2$s, configured=%3$d, userId=%4$d}",
                name, token, configured, user.getId());
    }


}
