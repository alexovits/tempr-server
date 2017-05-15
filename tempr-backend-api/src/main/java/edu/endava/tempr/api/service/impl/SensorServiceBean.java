package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.exception.SensorNotFoundException;
import edu.endava.tempr.api.service.SensorService;
import edu.endava.tempr.model.Sensor;
import edu.endava.tempr.repository.HeatingCircuitRepository;
import edu.endava.tempr.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Sensor findOne(Long sensorId) throws SensorNotFoundException {
        Sensor sensor = sensorRepository.findOne(sensorId);
        if(sensor == null) throw new SensorNotFoundException(String.format("No sensor found with unique primary key ID: %1$d", sensorId));
        return sensor;
    }

    @Override
    public Sensor findByChipId(Long chipId) throws SensorNotFoundException {
        Sensor sensor = sensorRepository.findByChipId(chipId);
        if(sensor == null) throw new SensorNotFoundException(String.format("No sensor found with chip UUID : %1$d", chipId));
        return sensor;
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
    public Sensor update(Sensor sensor) throws SensorNotFoundException {
        Sensor updatedSensor = sensorRepository.findByChipId(sensor.getChipId());
        if(updatedSensor == null) throw new SensorNotFoundException(String.format("No sensor found with this Chip ID: %1$d", updatedSensor.getChipId()));
        LOG.info("Successfully updated with Chip ID: {}", sensor.getChipId());
        return sensorRepository.save(sensor);
    }

    @Override
    public void delete(Long id) {
        sensorRepository.delete(id);
    }
}
