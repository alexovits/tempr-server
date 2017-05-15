package edu.endava.tempr.api.service;

import edu.endava.tempr.api.exception.HeatingCircuitNotFoundException;
import edu.endava.tempr.api.exception.ThermostatNotFoundException;
import edu.endava.tempr.common.HeatingCircuitDto;
import edu.endava.tempr.model.HeatingCircuit;

import java.util.List;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public interface HeatingCircuitService {
    HeatingCircuit create(HeatingCircuitDto heatingCircuitDto) throws ThermostatNotFoundException;
    HeatingCircuit findByChipId(long chipId) throws HeatingCircuitNotFoundException;
    HeatingCircuit findOne(long heatingCircuitId) throws HeatingCircuitNotFoundException;
    HeatingCircuit update(HeatingCircuit heatingCircuit) throws HeatingCircuitNotFoundException;
    Integer getDesiredTemperature(long heatingCircuitId) throws HeatingCircuitNotFoundException;
    void updateDesiredTemperature(long heatingCircuitId, int desiredTemperature) throws HeatingCircuitNotFoundException;
    Boolean getAiFlag(long heatingCircuitId) throws HeatingCircuitNotFoundException;
    void updateAiFlag(long heatingCircuitId, boolean aiFlag) throws HeatingCircuitNotFoundException;
    Integer getSuggestedTemperature(long heatingCircuitId) throws HeatingCircuitNotFoundException;
    void updateSuggestedTemperature(long heatingCircuitId, int suggestedTemperature) throws HeatingCircuitNotFoundException;
    boolean sensorBelongsToUser(String userName, HeatingCircuit heatingCircuit);
}
