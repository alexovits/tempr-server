package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.SensorLog;
import edu.endava.tempr.repository.SensorLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public class SensorLogServiceBean implements SensorLogService{

    private static final Logger LOG = LoggerFactory.getLogger(SensorLogService.class);

    @Autowired
    private SensorLogRepository sensorLogRepository;

    @Override
    public SensorLog findOne(Long sensorLogId) {
        return sensorLogRepository.findOne(sensorLogId);
    }

    @Override
    public SensorLog create(SensorLog sensorLog) {
        LOG.info("Saving sensor log");
        SensorLog savedSensorLog = sensorLogRepository.save(sensorLog);
        LOG.info("Successfully saved sensor log");
        return savedSensorLog;
    }

    @Override
    public SensorLog getLatest(HeatingCircuit heatingCircuit) {
        return null;
    }
}
