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

    @Column(name = "desiredTemperature")
    private Integer desiredTemperature;

    @ManyToOne
    @Column(nullable = false)
    private Thermostat thermostat;

    @OneToOne
    @Column(nullable = false)
    private HeatingSource heatingSource;

    @OneToOne
    @Column(nullable = false)
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

    public Integer getDesiredTemperature() {
        return desiredTemperature;
    }

    public void setDesiredTemperature(Integer desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
    }

    public Thermostat getThermostat() {
        return thermostat;
    }

    public void setThermostat(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("Sensor{name = %s, desiredTemperature = %s, sensorID = %s, heatingSourceID = %s}", name, desiredTemperature, sensor.getSensorId(), heatingSource.getId());
    }
}
