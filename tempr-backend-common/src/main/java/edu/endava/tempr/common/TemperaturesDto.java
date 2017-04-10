package edu.endava.tempr.common;

/**
 * Created by zsoltszabo on 4/9/17.
 */
public class TemperaturesDto{
    private int temperature, suggestedTemperature, desiredTemperature;
    private long chipId;
    private boolean suggestionFlag;

    public TemperaturesDto(int temperature, int suggestedTemperature, int desiredTemperature, long chipId, boolean suggestionFlag){
        this.temperature = temperature;
        this.suggestedTemperature = suggestedTemperature;
        this.desiredTemperature = desiredTemperature;
        this.chipId = chipId;
        this.suggestionFlag = suggestionFlag;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getDesiredTemperature() {
        return desiredTemperature;
    }

    public void setDesiredTemperature(int desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
    }

    public long getChipId() {
        return chipId;
    }

    public void setChipId(long chipId) {
        this.chipId = chipId;
    }

    public int getSuggestedTemperature() {
        return suggestedTemperature;
    }

    public void setSuggestedTemperature(int suggestedTemperature) {
        this.suggestedTemperature = suggestedTemperature;
    }

    public boolean isSuggestionFlag() {
        return suggestionFlag;
    }

    public void setSuggestionFlag(boolean suggestionFlag) {
        this.suggestionFlag = suggestionFlag;
    }
}
