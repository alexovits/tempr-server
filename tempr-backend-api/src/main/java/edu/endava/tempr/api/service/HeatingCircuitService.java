package edu.endava.tempr.api.service;

import edu.endava.tempr.model.HeatingCircuit;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public interface HeatingCircuitService {
    HeatingCircuit create(HeatingCircuit heatingCircuit, String thermostatToken);
    HeatingCircuit findByChipId(long chipId);
    HeatingCircuit findOne(long heatingCircuitId);
    HeatingCircuit update(HeatingCircuit heatingCircuit);
    Integer getDesiredTemperature(long heatingCircuitId);
    void updateDesiredTemperature(long heatingCircuitId, int desiredTemperature);
}
