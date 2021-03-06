package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 4/9/17.
 */
public class TemperaturesDto{
    private Integer currentTemperature, desiredTemperature;
    private Double suggestedTemperature;
    private Long heatingCircuitId, chipId;
    private Boolean AIFlag;
    private String heatingCircuitName;

    public TemperaturesDto(Integer currentTemperature, Double suggestedTemperature, Integer desiredTemperature, Long heatingCircuitId, Boolean AIFlag, String heatingCircuitName, Long chipId){
        this.currentTemperature = currentTemperature;
        this.suggestedTemperature = suggestedTemperature;
        this.desiredTemperature = desiredTemperature;
        this.heatingCircuitId = heatingCircuitId;
        this.AIFlag = AIFlag;
        this.heatingCircuitName = heatingCircuitName;
        this.chipId = chipId;
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

    public Double getSuggestedTemperature() {
        return suggestedTemperature;
    }

    public void setSuggestedTemperature(Double suggestedTemperature) {
        this.suggestedTemperature = suggestedTemperature;
    }

    public Boolean isAIFlag() {
        return AIFlag;
    }

    public void setAIFlag(boolean AIFlag) {
        this.AIFlag = AIFlag;
    }

    public void setHeatingCircuitName(String heatingCircuitName){
        this.heatingCircuitName = heatingCircuitName;
    }

    public String getHeatingCircuitName(){
        return heatingCircuitName;
    }

    public Long getChipId() {
        return chipId;
    }

    public void setChipId(Long chipId) {
        this.chipId = chipId;
    }

    @Override
    public String toString() {
        return String.format("TemperaturesDTO{ currentTemperature=%1$d, suggestedTemperature=%2$,.2f, desiredTemperature=%3$d, heatingCircuitId=%4$d, heatingCircuitName=%5$s, AIFlag=%6$b, chipId=%7$d}",
                currentTemperature, suggestedTemperature, desiredTemperature, heatingCircuitId, heatingCircuitName, AIFlag, chipId);
    }
}
