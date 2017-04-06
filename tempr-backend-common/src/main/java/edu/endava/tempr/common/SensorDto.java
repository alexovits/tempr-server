package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 4/4/17.
 */
public class SensorDto extends AbstractDto{
    private Long sensorId;


    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }
}
