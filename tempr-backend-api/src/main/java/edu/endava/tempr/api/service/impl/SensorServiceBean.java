package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.SensorService;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.Sensor;
import edu.endava.tempr.repository.HeatingCircuitRepository;
import edu.endava.tempr.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

/**
 * Created by zsoltszabo on 4/8/17.
 */
@Service
public class SensorServiceBean implements SensorService{

    private static final Logger LOG = LoggerFactory.getLogger(SensorService.class);

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private HeatingCircuitRepository heatingCircuitRepository;

    @Override
    public Sensor findOne(Long id) {
        return sensorRepository.findOne(id);
    }

    @Override
    public Sensor findBySensorId(Long sensorId) {
        return sensorRepository.findBySensorId(sensorId);
    }

    @Override
    public Sensor create(Sensor sensor, Long heatingCircuitId) {
        HeatingCircuit heatingCircuit;
        if((heatingCircuit = heatingCircuitRepository.findOne(heatingCircuitId)) == null){
            throw new InvalidParameterException("Couldn't find Heating Circuit™ with the id: " + heatingCircuitId);
        }
        LOG.info("Saving sensor with id {}", sensor.getSensorId());
        Sensor savedSensor = sensorRepository.save(sensor);
        LOG.info("Successfully saved sensor with id {}", sensor.getSensorId());
        heatingCircuit.setSensor(savedSensor);
        heatingCircuitRepository.save(heatingCircuit);
        return savedSensor;
    }

    @Override
    public Sensor update(Sensor sensor) {
        Sensor updatedSensor = sensorRepository.findBySensorId(sensor.getSensorId());
        if(updatedSensor == null){
            LOG.error("No sensor found with this id: {}", updatedSensor.getSensorId());
        }
        LOG.info("Successfully updated with id {}", sensor.getSensorId());
        //sensor.setId(updatedSensor.getId());
        return sensorRepository.save(sensor);
    }

    @Override
    public void delete(Long id) {

    }
}
