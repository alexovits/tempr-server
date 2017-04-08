package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.SensorService;
import edu.endava.tempr.model.Sensor;
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

    @Override
    public Sensor findOne(Long sensorId) {
        return sensorRepository.findOne(sensorId);
    }

    @Override
    public Sensor create(Sensor sensor) {
        LOG.info("Saving sensor with id {}", sensor.getSensorId());
        Sensor savedSensor = sensorRepository.save(sensor);
        LOG.info("Successfully saved sensor with id {}", sensor.getSensorId());
        return savedSensor;
    }

    @Override
    public Sensor update(Sensor sensor) {
        Sensor updatedSensor = sensorRepository.findBySensorId(sensor.getSensorId());
        if(updatedSensor == null){
            LOG.error("No sensor found with this id: {}", updatedSensor.getSensorId());
        }
        LOG.info("Successfully updated with id {}", sensor.getSensorId());
        sensor.setId(updatedSensor.getId());
        return sensorRepository.save(sensor);
    }

    @Override
    public void delete(Long id) {

    }
}
