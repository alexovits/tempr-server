package edu.endava.tempr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    public Thermostat() {
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
}
