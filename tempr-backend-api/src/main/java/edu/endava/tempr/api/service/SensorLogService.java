package edu.endava.tempr.api.service;

import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.SensorLog;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public interface SensorLogService {
    SensorLog findOne(Long sensorLogId);
    SensorLog create(int sensorLog, HeatingCircuit heatingCircuit);
    SensorLog getLatestLog(Long heatingCircuitId);
    Integer getLatestTemperature(Long heatingCircuitId);
}
