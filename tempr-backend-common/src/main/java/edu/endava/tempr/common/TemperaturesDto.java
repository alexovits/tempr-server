package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 4/9/17.
 */
public class TemperaturesDto{
    private Integer currentTemperature, suggestedTemperature, desiredTemperature;
    private Long heatingCircuitId;
    private Boolean AIFlag;

    public TemperaturesDto(Integer currentTemperature, Integer suggestedTemperature, Integer desiredTemperature, Long heatingCircuitId, Boolean AIFlag){
        this.currentTemperature = currentTemperature;
        this.suggestedTemperature = suggestedTemperature;
        this.desiredTemperature = desiredTemperature;
        this.heatingCircuitId = heatingCircuitId;
        this.AIFlag = AIFlag;
    }

    public Integer getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Integer getDesiredTemperature() {
        return desiredTemperature;
    }

    public void setDesiredTemperature(int desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
    }

    public Long getHeatingCircuitId() {
        return heatingCircuitId;
    }

    public void setHeatingCircuitId(long heatingCircuitId) {
        this.heatingCircuitId = heatingCircuitId;
    }

    public Integer getSuggestedTemperature() {
        return suggestedTemperature;
    }

    public void setSuggestedTemperature(int suggestedTemperature) {
        this.suggestedTemperature = suggestedTemperature;
    }

    public Boolean isAIFlag() {
        return AIFlag;
    }

    public void setAIFlag(boolean AIFlag) {
        this.AIFlag = AIFlag;
    }
}
