package edu.endava.tempr.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by zsoltszabo on 4/4/17.
 */

@Entity
public class Sensor extends BaseEntity{

    @Column(name = "chipId", unique = true, nullable = false)
    private Long chipId;

    public Long getChipId() {
        return chipId;
    }

    public void setChipId(Long chipId) {
        this.chipId = chipId;
    }

    @Override
    public String toString(){
        return String.format("Sensor{sensorId = %s}", chipId);
    }
}
