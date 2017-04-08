package edu.endava.tempr.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltszabo on 4/3/17.
 */
@Entity
@Table(name = "HeatingCircuit")
public class HeatingCircuit extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "desiredTemperature")
    private Integer desiredTemperature;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Thermostat thermostat;

    @OneToOne
    @JoinColumn(nullable = false)
    private HeatingSource heatingSource;

    @OneToOne
    @JoinColumn(nullable = false)
    private Sensor sensor;

    @OneToMany(mappedBy = "heatingCircuit", fetch = FetchType.EAGER)
    private List<SensorLog> sensorLogList = new ArrayList<>();

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

    public List<SensorLog> getSensorLogList() {
        return sensorLogList;
    }

    public void setSensorLogList(List<SensorLog> sensorLogList) {
        this.sensorLogList = new ArrayList<>();
        sensorLogList.forEach(hc -> this.sensorLogList.add(hc));
    }

    public void addSensorLog(SensorLog sensorLog){
        if(!sensorLogList.contains(sensorLog)){
            sensorLogList.add(sensorLog);
        }
    }

    @Override
    public String toString(){
        return String.format("Sensor{name = %s, desiredTemperature = %s, sensorID = %s, heatingSourceID = %s}", name, desiredTemperature, sensor.getSensorId(), heatingSource.getId());
    }
}
