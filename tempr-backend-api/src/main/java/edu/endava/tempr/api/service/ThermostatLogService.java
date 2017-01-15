package edu.endava.tempr.api.service;

import edu.endava.tempr.model.ThermostatLog;

import java.util.List;

/**
 * Created by zsoltszabo on 05/01/2017.
 */
public interface ThermostatLogService {
    ThermostatLog findOne(Long id);
    ThermostatLog create(ThermostatLog thermostatLog);
    void delete(Long id);
    ThermostatLog update(ThermostatLog thermostatLog);
    ThermostatLog getLatest(String token);
    List<ThermostatLog> getLastTenDays(String token);
}
