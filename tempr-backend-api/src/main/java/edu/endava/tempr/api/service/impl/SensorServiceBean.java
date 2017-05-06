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
    public Sensor findOne(Long sensorId) {
        return sensorRepository.findOne(sensorId);
    }

    @Override
    public Sensor findByChipId(Long chipId) {
        return sensorRepository.findByChipId(chipId);
    }

    @Override
    public Sensor create(long chipId) {
        Sensor sensor = new Sensor();
        sensor.setChipId(chipId);
        LOG.info("Saving sensor with Chip ID: {}", sensor.getChipId());
        Sensor savedSensor = sensorRepository.save(sensor);
        LOG.info("Successfully saved sensor with Chip ID: {}", sensor.getChipId());
        return savedSensor;
    }

    @Override
    public Sensor update(Sensor sensor) {
        Sensor updatedSensor = sensorRepository.findByChipId(sensor.getChipId());
        if(updatedSensor == null){
            LOG.error("No sensor found with this Chip ID: {}", updatedSensor.getChipId());
        }
        LOG.info("Successfully updated with Chip ID: {}", sensor.getChipId());
        //sensor.setId(updatedSensor.getId());
        return sensorRepository.save(sensor);
    }

    @Override
    public void delete(Long id) {

    }
}
