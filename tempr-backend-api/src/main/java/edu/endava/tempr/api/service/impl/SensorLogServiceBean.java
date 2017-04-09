package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.SensorLog;
import edu.endava.tempr.repository.SensorLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by zsoltszabo on 4/8/17.
 */
@Service
public class SensorLogServiceBean implements SensorLogService{

    private static final Logger LOG = LoggerFactory.getLogger(SensorLogService.class);

    @Autowired
    private SensorLogRepository sensorLogRepository;

    @Override
    public SensorLog findOne(Long sensorLogId) {
        return sensorLogRepository.findOne(sensorLogId);
    }

    @Override
    public SensorLog create(int temperature, HeatingCircuit heatingCircuit) {
        SensorLog sensorLog = new SensorLog();
        sensorLog.setTemperature(temperature);
        //TO_DO Not serializing right...
        sensorLog.setLogTimeStamp(LocalDateTime.now());
        LOG.info("FASZOM: {}", sensorLog.getLogTimeStamp());
        sensorLog.setHeatingCircuit(heatingCircuit);
        SensorLog savedSensorLog = sensorLogRepository.save(sensorLog);
        LOG.info("Successfully saved sensor log");
        return savedSensorLog;
    }

    @Override
    public SensorLog getLatest(HeatingCircuit heatingCircuit) {
        return null;
    }
}
