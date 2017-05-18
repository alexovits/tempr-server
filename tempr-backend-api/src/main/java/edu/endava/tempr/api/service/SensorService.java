package edu.endava.tempr.api.service;

import edu.endava.tempr.api.exception.SensorNotFoundException;
import edu.endava.tempr.model.Sensor;
/**
 * Created by zsoltszabo on 4/8/17.
 */
public interface SensorService {
    Sensor findOne(Long sensorId) throws SensorNotFoundException;
    Sensor findByChipId(Long chipId) throws SensorNotFoundException;
    Sensor create(long chipId);
    Sensor update(Sensor sensor) throws SensorNotFoundException;
    void delete(Long id);
}
