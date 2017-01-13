package edu.endava.tempr.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltszabo on 02/01/2017.
 */
@Entity
@Table(name="ThermostatLog", indexes={@Index(name="ThermostatLog_Thermostat_Index", columnList = "thermostat_token")})
public class ThermostatLog extends BaseEntity {

    @Column(name = "logTimeStamp", nullable = false)
    private String logTimeStamp;

    @Column(name = "thermostat_token", nullable = false)
    private String token;

    @Column(name = "external_temperature")
    private String extTemp;

    @Column(name = "internal_temperature")
    private String intTemp;

    public String getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(String logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }

    public String getExtTemp() {
        return extTemp;
    }

    public void setExtTemp(String extTemp) {
        this.extTemp = extTemp;
    }

    public String getIntTemp() {
        return intTemp;
    }

    public void setIntTemp(String intTemp) {
        this.intTemp = intTemp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ThermostatLog{" +
                "logTimeStamp='" + logTimeStamp + '\'' +
                ", extTemp='" + extTemp + '\'' +
                ", intTemp='" + intTemp + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
