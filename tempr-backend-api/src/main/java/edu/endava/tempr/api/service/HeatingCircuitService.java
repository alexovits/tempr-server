package edu.endava.tempr.api.service;

import edu.endava.tempr.model.HeatingCircuit;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public interface HeatingCircuitService {
    HeatingCircuit create(HeatingCircuit heatingCircuit, String thermostatToken);
    HeatingCircuit findByChipId(Long chipId);
    HeatingCircuit update(HeatingCircuit heatingCircuit);
}
