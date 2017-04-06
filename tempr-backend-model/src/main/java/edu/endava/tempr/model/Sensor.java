package edu.endava.tempr.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by zsoltszabo on 4/4/17.
 */
@Entity
public class Sensor extends BaseEntity{

    @Column(name = "sensorId", unique = true, nullable = false)
    private Long sensorId;

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String toString(){
        return String.format("Sensor{sensorId = %s}", sensorId);
    }
}
