package edu.endava.tempr.api.service;

import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;

import java.util.List;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
public interface ThermostatService {
    List<Thermostat> findAll();

    Thermostat findOne(String token);

    Thermostat createThermostat(Thermostat thermostat);

    Thermostat updateThermostat(Thermostat thermostat);

    void deletThermostat(Long id);
}
