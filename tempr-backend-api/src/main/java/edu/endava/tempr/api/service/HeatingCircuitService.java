package edu.endava.tempr.api.service;

import edu.endava.tempr.api.exception.HeatingCircuitNotFoundException;
import edu.endava.tempr.api.exception.OutOfHistogramRangeException;
import edu.endava.tempr.api.exception.SensorLogNotFoundException;
import edu.endava.tempr.common.HeatingCircuitDto;
import edu.endava.tempr.model.HeatingCircuit;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public interface HeatingCircuitService {
    HeatingCircuit create(HeatingCircuitDto heatingCircuitDto);
    HeatingCircuit findByChipId(long chipId) throws HeatingCircuitNotFoundException;
    HeatingCircuit findOne(long heatingCircuitId) throws HeatingCircuitNotFoundException;
    HeatingCircuit update(HeatingCircuit heatingCircuit) throws HeatingCircuitNotFoundException;
    Integer getDesiredTemperature(long heatingCircuitId) throws HeatingCircuitNotFoundException;
    void updateDesiredTemperature(long heatingCircuitId, int desiredTemperature) throws HeatingCircuitNotFoundException;
    Boolean getAiFlag(long heatingCircuitId) throws HeatingCircuitNotFoundException;
    void updateAiFlag(long heatingCircuitId, boolean aiFlag) throws HeatingCircuitNotFoundException;
    Double getSuggestedTemperature(long heatingCircuitId) throws HeatingCircuitNotFoundException, SensorLogNotFoundException, OutOfHistogramRangeException;
    boolean sensorBelongsToUser(String userName, HeatingCircuit heatingCircuit);
    Long getChipId(Long heatingCircuitId);
}
