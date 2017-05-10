package edu.endava.tempr.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by zsoltszabo on 4/6/17.
 */
@Entity
@Table(name="SensorLog", indexes={@Index(name="HeatingCircuit_Index", columnList = "heatingCircuit")})
public class SensorLog extends BaseEntity{

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime logTimeStamp;

    @ManyToOne
    @JoinColumn(nullable = false)
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

    @Override
    public String toString() {
        return String.format("SensorLog{ timeStamp=%1$s, heatingCircuitId=%2$d, temperature=%3$d}",
                logTimeStamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), heatingCircuit.getId(), temperature);
    }
}
