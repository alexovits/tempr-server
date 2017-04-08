package edu.endava.tempr.common;

import java.time.LocalDateTime;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public class SensorLogDto extends AbstractDto{
    private LocalDateTime logTimeStamp;

    private Long heatingCircuitId;

    private Integer temperature;

    public LocalDateTime getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(LocalDateTime logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }

    public Long getHeatingCircuitId() {
        return heatingCircuitId;
    }

    public void setHeatingCircuitId(Long heatingCircuitId) {
        this.heatingCircuitId = heatingCircuitId;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }
}
