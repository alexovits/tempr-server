package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.SensorLog;
import edu.endava.tempr.repository.HeatingCircuitRepository;
import edu.endava.tempr.repository.SensorLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zsoltszabo on 4/8/17.
 */
@Service
public class SensorLogServiceBean implements SensorLogService{

    private static final Logger LOG = LoggerFactory.getLogger(SensorLogService.class);

    private static final int LAST_INTERVAL = 7;

    @Autowired
    private SensorLogRepository sensorLogRepository;

    @Autowired
    private HeatingCircuitRepository heatingCircuitRepository;

    @Override
    public SensorLog findOne(Long sensorLogId) {
        return sensorLogRepository.findOne(sensorLogId);
    }

    @Override
    public SensorLog create(int temperature, HeatingCircuit heatingCircuit) {
        SensorLog sensorLog = new SensorLog();
        sensorLog.setTemperature(temperature);
        sensorLog.setLogTimeStamp(LocalDateTime.now());
        sensorLog.setHeatingCircuit(heatingCircuit);
        SensorLog savedSensorLog = sensorLogRepository.save(sensorLog);
        LOG.info("Successfully saved sensor log for Heating Circuit with ID {} and temperature {} with timeStamp {}", heatingCircuit.getId(), temperature, savedSensorLog.getLogTimeStamp());
        return savedSensorLog;
    }

    @Override
    public SensorLog create(LocalDateTime timeStamp, int temperature, HeatingCircuit heatingCircuit) {
        SensorLog sensorLog = new SensorLog();
        sensorLog.setTemperature(temperature);
        sensorLog.setLogTimeStamp(timeStamp);
        sensorLog.setHeatingCircuit(heatingCircuit);
        SensorLog savedSensorLog = sensorLogRepository.save(sensorLog);
        LOG.info("Successfully saved sensor log for Heating Circuit with ID {} and temperature {} with timeStamp {}", heatingCircuit.getId(), temperature, savedSensorLog.getLogTimeStamp());
        return savedSensorLog;
    }

    // Returns the entire log object of the latest log
    @Override
    public SensorLog getLatestLog(Long heatingCircuitId) {
        return sensorLogRepository.findFirstByHeatingCircuitIdOrderByLogTimeStampDesc(heatingCircuitId);
    }

    // Returns only the temperature value of the latest log
    @Override
    public Integer getLatestTemperature(Long heatingCircuitId) {
        Integer latestTemp = null;
        latestTemp = getLatestLog(heatingCircuitId).getTemperature();
        return latestTemp;
    }

    @Override
    public List<SensorLog> getLogsSince(Long heatingCircuitId, LocalDateTime timeStamp) {
        if(heatingCircuitRepository.findOne(heatingCircuitId) == null){
            throw new InvalidParameterException(String.format("Couldn't find a heating circuit with the id %1$d", heatingCircuitId));
        }
        return sensorLogRepository.findByHeatingCircuitIdAndLogTimeStampGreaterThan(heatingCircuitId, timeStamp);
    }

    @Override
    public List<SensorLog> getLastWeeksLogs(Long heatingCircuitId) {
        return getLogsSince(heatingCircuitId, LocalDateTime.now().minusDays(LAST_INTERVAL));
    }
}
