package edu.endava.tempr.api.service;

import edu.endava.tempr.api.exception.SensorLogNotFoundException;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.SensorLog;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public interface SensorLogService {
    SensorLog findOne(Long sensorLogId) throws SensorLogNotFoundException;
    SensorLog create(int temperature, HeatingCircuit heatingCircuit);
    SensorLog create(LocalDateTime timeStamp, int temperature, HeatingCircuit heatingCircuit);
    SensorLog getLatestLog(Long heatingCircuitId) throws SensorLogNotFoundException;
    Integer getLatestTemperature(Long heatingCircuitId) throws SensorLogNotFoundException;
    List<SensorLog> getLogsSince(Long heatingCircuitId, LocalDateTime timeStamp) throws SensorLogNotFoundException;
    List<SensorLog> getLastWeeksLogs(Long heatingCircuitId) throws SensorLogNotFoundException;
}
