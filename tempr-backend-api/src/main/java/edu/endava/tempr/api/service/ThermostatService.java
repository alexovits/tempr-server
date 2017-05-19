package edu.endava.tempr.api.service;

import edu.endava.tempr.api.exception.*;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;

import java.util.List;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
public interface ThermostatService {
    Thermostat findOne(String token) throws ThermostatNotFoundException;
    Thermostat createThermostat(User user, Thermostat thermostat) throws ThermostatAlreadyCreated;
    Thermostat updateThermostat(Thermostat thermostat) throws ThermostatNotFoundException;
    Thermostat findByUserId(Long userId) throws UserNotFoundException, ThermostatNotFoundException;
    Thermostat findByUserName(String username) throws UserNotFoundException, ThermostatNotFoundException;
    List getTemperatures(String thermostatToken) throws ThermostatNotFoundException, OutOfHistogramRangeException, HeatingCircuitNotFoundException;
    void deleteThermostat(Long id);
    Thermostat configureThermostat(String thermostatToken) throws ThermostatAlreadyConfiguredException, ThermostatNotFoundException;
    Thermostat unConfigureThermostat(String thermostatToken) throws ThermostatAlreadyConfiguredException, ThermostatNotFoundException;
}
