package edu.endava.tempr.api.service;

import edu.endava.tempr.common.TemperaturesDto;

import java.util.List;

/**
 * Created by zsoltszabo on 4/9/17.
 */
public interface SimulatorService {
    void addHeatingCircuit(long chipId);
    void logTemperature(int temperature, long chipId);
    void setSuggestedTemperature(int temperature, long chipId);
    void setDesiredTemperature(int temperature, long chipId);
    void setSuggestionFlag(boolean suggestionFlag);
    List<TemperaturesDto> getSimulatedLogList();
}
