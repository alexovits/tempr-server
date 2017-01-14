package edu.endava.tempr.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zsoltszabo on 02/01/2017.
 */
@Entity
@Table(name="ThermostatLog", indexes={@Index(name="ThermostatLog_Thermostat_Index", columnList = "thermostat_token")})
public class ThermostatLog extends BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "logTimeStamp", nullable = false)
    private Date logTimeStamp;

    @Column(name = "thermostat_token", nullable = false)
    private String token;

    @Column(name = "external_temperature")
    private String extTemp;

    @Column(name = "internal_temperature")
    private String intTemp;


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

    public void setLogTimeStamp(Date logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }

    public Date getLogTimeStamp() {
        return logTimeStamp;
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
