package edu.endava.tempr.api.service;

import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;

import java.util.List;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
public interface ThermostatService {
    Thermostat findOne(String token);
    Thermostat createThermostat(User user, Thermostat thermostat);
    Thermostat updateThermostat(Thermostat thermostat);
    Thermostat findByUserId(Long userId);
    List getTemperatures(String thermostatToken);
    void deleteThermostat(Long id);
}
