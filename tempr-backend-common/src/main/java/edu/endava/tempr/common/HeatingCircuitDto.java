package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 4/4/17.
 */
public class HeatingCircuitDto extends AbstractDto{

    private String name;

    private String thermostatToken;

    private Integer desiredTemperature;

    private Integer suggestedTemperature;

    private HeatingSourceDto heatingSourceDto;

    private Long sensorChipId;

    private Boolean aiFlag;

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

    public Long getSensorChipId() {
        return sensorChipId;
    }

    public void setSensorChipId(Long sensorChipId) {
        this.sensorChipId = sensorChipId;
    }

    public Integer getDesiredTemperature() {
        return desiredTemperature;
    }

    public void setDesiredTemperature(Integer desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
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
        return String.format("HeatingCircuitDTO{ name=%1$s, thermostatToken=%2$s, desiredTemperature=%3$d, suggestedTemperature=%4$d, heatingSource=%5$s, sensorChipId=%6$s, aiFlag=%7$s}",
                name, thermostatToken, desiredTemperature, suggestedTemperature, heatingSourceDto, sensorChipId, aiFlag);
    }
}
