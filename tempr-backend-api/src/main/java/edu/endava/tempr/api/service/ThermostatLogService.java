package edu.endava.tempr.api.service;

import edu.endava.tempr.model.ThermostatLog;

/**
 * Created by zsoltszabo on 05/01/2017.
 */
public interface ThermostatLogService {
    public ThermostatLog findOne(Long id);
    public ThermostatLog create(ThermostatLog thermostatLog);
    public void delete(Long id);
    public ThermostatLog update(ThermostatLog thermostatLog);
    public ThermostatLog getLatest();
}
