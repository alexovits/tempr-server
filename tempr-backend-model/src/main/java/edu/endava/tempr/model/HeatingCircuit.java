package edu.endava.tempr.model;

import javax.persistence.*;

/**
 * Created by zsoltszabo on 4/3/17.
 */
@Entity
@Table(name = "HeatingCircuits")
public class HeatingCircuit extends BaseEntity{
    @Column(name = "name")
    private String name;

    @ManyToOne
    private Thermostat thermostat;

    @OneToOne
    private HeatingSource heatingSource;

    @OneToOne
    private Sensor sensor;

    public HeatingSource getHeatingSource() {
        return heatingSource;
    }

    public void setHeatingSource(HeatingSource heatingSource) {
        this.heatingSource = heatingSource;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
