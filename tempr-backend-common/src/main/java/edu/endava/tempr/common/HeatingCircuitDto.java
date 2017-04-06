package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 4/4/17.
 */
public class HeatingCircuitDto extends AbstractDto{

    private String name;

    private String thermostatToken;

    private Integer desiredTemperature;

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

    public String getThermostatToken() {
        return thermostatToken;
    }

    public void setThermostatToken(String thermostatToken) {
        this.thermostatToken = thermostatToken;
    }

    public HeatingSourceDto getHeatingSourceDto() {
        return heatingSourceDto;
    }

    public void setHeatingSourceDto(HeatingSourceDto thermostatId) {
        this.heatingSourceDto = heatingSourceDto;
    }

    public SensorDto getSensor() {
        return sensorDto;
    }

    public void setSensor(SensorDto sensorDto) {
        this.sensorDto = sensorDto;
    }

    public Integer getDesiredTemperature() {
        return desiredTemperature;
    }

    public void setDesiredTemperature(Integer desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
    }
}
