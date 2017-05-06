package edu.endava.tempr.api.service;

import edu.endava.tempr.common.HeatingCircuitDto;
import edu.endava.tempr.model.HeatingCircuit;

import java.util.List;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public interface HeatingCircuitService {
    HeatingCircuit create(HeatingCircuitDto heatingCircuitDto);
    HeatingCircuit findByChipId(long chipId);
    HeatingCircuit findOne(long heatingCircuitId);
    HeatingCircuit update(HeatingCircuit heatingCircuit);
    Integer getDesiredTemperature(long heatingCircuitId);
    void updateDesiredTemperature(long heatingCircuitId, int desiredTemperature);
    Boolean getAiFlag(long heatingCircuitId);
    void updateAiFlag(long heatingCircuitId, boolean aiFlag);
    Integer getSuggestedTemperature(long heatingCircuitId);
    void updateSuggestedTemperature(long heatingCircuitId, int suggestedTemperature);

}
