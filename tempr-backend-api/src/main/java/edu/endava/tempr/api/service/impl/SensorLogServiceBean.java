package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.exception.SensorLogNotFoundException;
import edu.endava.tempr.api.service.HeatingCircuitService;
import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.SensorLog;
import edu.endava.tempr.repository.SensorLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private HeatingCircuitService heatingCircuitService;

    @Override
    public SensorLog findOne(Long sensorLogId) throws SensorLogNotFoundException {
        SensorLog sensorLog = sensorLogRepository.findOne(sensorLogId);
        if(sensorLog == null) throw new SensorLogNotFoundException(String.format("Couldn't find sensor log with ID %1$d", sensorLog));
        return sensorLog;
    }

    @Override
    public SensorLog create(int temperature, HeatingCircuit heatingCircuit) {
        SensorLog sensorLog = new SensorLog();
        sensorLog.setTemperature(temperature);
        sensorLog.setLogTimeStamp(LocalDateTime.now());
        sensorLog.setHeatingCircuit(heatingCircuit);
        SensorLog savedSensorLog = sensorLogRepository.save(sensorLog);
        //LOG.info("Successfully saved sensor log for Heating Circuit with ID {} and temperature {} with timeStamp {}", heatingCircuit.getId(), temperature, savedSensorLog.getLogTimeStamp());
        return savedSensorLog;
    }

    @Override
    public SensorLog create(LocalDateTime timeStamp, int temperature, HeatingCircuit heatingCircuit) {
        SensorLog sensorLog = new SensorLog();
        sensorLog.setTemperature(temperature);
        sensorLog.setLogTimeStamp(timeStamp);
        sensorLog.setHeatingCircuit(heatingCircuit);
        SensorLog savedSensorLog = sensorLogRepository.save(sensorLog);
        //LOG.info("Successfully saved sensor log for Heating Circuit with ID {} and temperature {} with timeStamp {}", heatingCircuit.getId(), temperature, savedSensorLog.getLogTimeStamp());
        return savedSensorLog;
    }

    // Returns the entire log object of the latest log
    @Override
    public SensorLog getLatestLog(Long heatingCircuitId) throws SensorLogNotFoundException {
        SensorLog latestLog = sensorLogRepository.findFirstByHeatingCircuitIdOrderByLogTimeStampDesc(heatingCircuitId);
        if(latestLog == null) throw new SensorLogNotFoundException(String.format("Heating Circuit with ID %1$d has no previous logs yet.", heatingCircuitId));
        return latestLog;
    }

    // Returns only the temperature value of the latest log
    @Override
    public Integer getLatestTemperature(Long heatingCircuitId) throws SensorLogNotFoundException {
        return getLatestLog(heatingCircuitId).getTemperature();
    }

    @Override
    public List<SensorLog> getLogsSince(Long heatingCircuitId, LocalDateTime timeStamp) throws SensorLogNotFoundException {
        List<SensorLog> sensorLogs = sensorLogRepository.findByHeatingCircuitIdAndLogTimeStampGreaterThan(heatingCircuitId, timeStamp);
        if(sensorLogs == null) throw new SensorLogNotFoundException(String.format("Heating Circuit with ID %1$d has no logs before %2$s.", heatingCircuitId));
        return sensorLogs;
    }

    @Override
    public List<SensorLog> getLastWeeksLogs(Long heatingCircuitId) throws SensorLogNotFoundException {
        return getLogsSince(heatingCircuitId, LocalDateTime.now().minusDays(LAST_INTERVAL));
    }
}
