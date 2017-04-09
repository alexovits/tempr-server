package edu.endava.tempr.api.service;

import edu.endava.tempr.model.Sensor;
/**
 * Created by zsoltszabo on 4/8/17.
 */
public interface SensorService {
    Sensor findOne(Long sensorId);
    Sensor findByChipId(Long chipId);
    Sensor create(Sensor sensor, Long heatingCircuitId);
    Sensor update(Sensor sensor);
    void delete(Long id);
}
