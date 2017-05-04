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

    @Column(name = "suggestedTemperature")
    private Integer suggestedTemperature;

    @Column(name = "aiFlag")
    private Boolean aiFlag;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Thermostat thermostat;

    @OneToOne
    @JoinColumn()
    private HeatingSource heatingSource;

    @OneToOne
    @JoinColumn(name = "chipId", referencedColumnName = "chipId")
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

    public Integer getSuggestedTemperature() {
        return suggestedTemperature;
    }

    public void setSuggestedTemperature(Integer suggestedTemperature) {
        this.suggestedTemperature = suggestedTemperature;
    }

    public Boolean getAiFlag() {
        return aiFlag;
    }

    public void setAiFlag(Boolean aiFlag) {
        this.aiFlag = aiFlag;
    }

    @Override
    public String toString() {
        return String.format("HeatingCircuit{ name=%1$s, thermostatToken=%2$s, desiredTemperature=%3$d, suggestedTemperature=%4$d, heatingSource=%5$s, sensor=%6$s, aiFlag=%7$b}",
                name, thermostat.getToken(), desiredTemperature, suggestedTemperature, heatingSource, sensor, aiFlag);
    }
}
