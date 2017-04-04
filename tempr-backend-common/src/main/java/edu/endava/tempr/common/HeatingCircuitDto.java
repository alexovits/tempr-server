package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 4/4/17.
 */
public class HeatingCircuitDto {

    private String name;

    private Long thermostatId;

    private HeatingSourceDto heatingSourceDto;

    private SensorDto sensorDto;

    public HeatingCircuitDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getThermostatId() {
        return thermostatId;
    }

    public void getThermostatId(Long thermostatId) {
        this.thermostatId = thermostatId;
    }

    public HeatingSourceDto getHeatingSource() {
        return heatingSourceDto;
    }

    public void setHeatingSource(HeatingSourceDto thermostatId) {
        this.heatingSourceDto = heatingSourceDto;
    }

    public SensorDto getSensor() {
        return sensorDto;
    }

    public void setSensor(SensorDto sensorDto) {
        this.sensorDto = sensorDto;
    }
}
