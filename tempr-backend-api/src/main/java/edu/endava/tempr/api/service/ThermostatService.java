package edu.endava.tempr.api.service;

import edu.endava.tempr.api.exception.InvalidThermostatTokenException;
import edu.endava.tempr.api.exception.ThermostatAlreadyConfiguredException;
import edu.endava.tempr.api.exception.UserNotFoundException;
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
    Thermostat findByUserId(Long userId) throws UserNotFoundException;
    Thermostat findByUserName(String username) throws UserNotFoundException;
    List getTemperatures(String thermostatToken);
    void deleteThermostat(Long id);
    void configureThermostat(String thermostatToken) throws ThermostatAlreadyConfiguredException, InvalidThermostatTokenException;
    void unConfigureThermostat(String thermostatToken) throws ThermostatAlreadyConfiguredException, InvalidThermostatTokenException;
}
