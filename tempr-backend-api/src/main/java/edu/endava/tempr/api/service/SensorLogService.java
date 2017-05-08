package edu.endava.tempr.api.service;

import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.SensorLog;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public interface SensorLogService {
    SensorLog findOne(Long sensorLogId);
    SensorLog create(int temperature, HeatingCircuit heatingCircuit);
    SensorLog create(LocalDateTime timeStamp, int temperature, HeatingCircuit heatingCircuit);
    SensorLog getLatestLog(Long heatingCircuitId);
    Integer getLatestTemperature(Long heatingCircuitId);
    List<SensorLog> getLogsSince(Long heatingCircuitId, LocalDateTime timeStamp);
    List<SensorLog> getLastWeeksLogs(Long heatingCircuitId);
}
