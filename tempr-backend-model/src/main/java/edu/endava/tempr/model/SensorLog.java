package edu.endava.tempr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by zsoltszabo on 4/6/17.
 */
@Entity
@Table(name="SensorLog")
public class SensorLog extends BaseEntity{
    @Column(nullable = false)
    private LocalDateTime logTimeStamp;

    @Column(nullable = false)
    private HeatingCircuit heatingCircuit;

    @Column(nullable = false)
    private Integer temperature;

    public LocalDateTime getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(LocalDateTime logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public HeatingCircuit getHeatingCircuit() {
        return heatingCircuit;
    }

    public void setHeatingCircuit(HeatingCircuit heatingCircuit) {
        this.heatingCircuit = heatingCircuit;
    }
}
