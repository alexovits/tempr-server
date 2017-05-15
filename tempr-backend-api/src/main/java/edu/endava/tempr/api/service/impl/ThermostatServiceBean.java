package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.exception.ThermostatAlreadyConfiguredException;
import edu.endava.tempr.api.exception.ThermostatNotFoundException;
import edu.endava.tempr.api.exception.UserNotFoundException;
import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.common.TemperaturesDto;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
import edu.endava.tempr.repository.ThermostatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
@Service
public class ThermostatServiceBean implements ThermostatService {

    private static final Logger LOG = LoggerFactory.getLogger(ThermostatServiceBean.class);
    private static final int tokenLength = 5;

    @Autowired
    ThermostatRepository thermostatRepository;

    @Autowired
    SensorLogService sensorLogService;

    @Autowired
    UserService userService;


    @Override
    public Thermostat findByUserId(Long userId) throws ThermostatNotFoundException {
        Thermostat thermostat = thermostatRepository.findByUserId(userId);
        if(thermostat == null) throw new ThermostatNotFoundException(String.format("Couldn't find a Thermostat with that is owned by user with id: %1$s", userId));
        return thermostat;
    }

    @Override
    public Thermostat findByUserName(String username) throws UserNotFoundException, ThermostatNotFoundException {
        return findByUserId(userService.findByName(username).getId());
    }

    @Override
    public void deleteThermostat(Long id) {
        LOG.info("Deleting thermostat with id: '{}'", id);
        thermostatRepository.delete(id);
    }

    @Override
    public Thermostat findOne(String token) throws ThermostatNotFoundException {
        LOG.info("Looking for thermostat with token: '{}'", token);
        Thermostat thermostat = thermostatRepository.findByToken(token);
        // If thermostat with id is not found
        if(thermostat == null){
            throw new ThermostatNotFoundException(String.format("Couldn't find a Thermostat with token %1$s", token));
        }
        return thermostat;
    }

    @Override
    public Thermostat createThermostat(User user, Thermostat thermostat) {
        // Set default properties for new thermostat
        thermostat.setToken(generateNewToken(user.getUsername()));
        thermostat.setConfigured((short) 0);
        thermostat.setUser(user);
        Thermostat savedThermostat = thermostatRepository.save(thermostat);
        LOG.info("Created thermostat with token: '{}'", savedThermostat.getToken());
        LOG.info("Created thermostat: '{}'", savedThermostat.toString());
        return savedThermostat;
    }

    @Override
    public Thermostat updateThermostat(Thermostat thermostat) throws ThermostatNotFoundException {
        Thermostat thermostatToUpdate = findOne(thermostat.getToken());
        LOG.info("Thermostat with token: '{}' was updated!", thermostat.getToken());
        return thermostatRepository.save(thermostat);
    }



    @Override
    public List<TemperaturesDto> getTemperatures(String thermostatToken) throws ThermostatNotFoundException {
        List temperatureList = new ArrayList<TemperaturesDto>();
        Thermostat thermostat = findOne(thermostatToken);
        // NullPointerException occurs when the user has no logs at all.
        thermostat.getHeatingCircuitList().forEach(
                hc -> {
                    try {
                        temperatureList.add(new TemperaturesDto(sensorLogService.getLatestTemperature(hc.getId()), hc.getSuggestedTemperature(), hc.getDesiredTemperature(), hc.getId(), hc.getAiFlag(), hc.getName()));
                    } catch (NullPointerException e) {
                        temperatureList.add(new TemperaturesDto(null, hc.getSuggestedTemperature(), hc.getDesiredTemperature(), hc.getId(), hc.getAiFlag(), hc.getName()));
                    }
                }
        );
        return temperatureList;
    }

    @Override
    public void configureThermostat(String thermostatToken) throws ThermostatAlreadyConfiguredException, ThermostatNotFoundException {
        Thermostat thermostat = findOne(thermostatToken);
        LOG.info("Configuring thermostat: {}", thermostat.getToken());
        // If it's already configured inform client
        if(thermostat.getConfigured() == 1) throw new ThermostatAlreadyConfiguredException(String.format("Thermostat with token %1$s configuration failed due to being already CONFIGURED.", thermostatToken));
        thermostat.setConfigured((short) 1); // Set it to configured
        updateThermostat(thermostat);
    }

    @Override
    public void unConfigureThermostat(String thermostatToken) throws ThermostatAlreadyConfiguredException, ThermostatNotFoundException {
        Thermostat thermostat = findOne(thermostatToken);
        LOG.info("Unconfiguring thermostat: {}", thermostat.getToken());
        // If it's already configured
        if(thermostat.getConfigured() == 0) throw new ThermostatAlreadyConfiguredException(String.format("Thermostat with token %1$s unconfiguration failed due to being already UNCONFIGURED.", thermostatToken));
        thermostat.setConfigured((short) 0); // Set it to unconfigured
        updateThermostat(thermostat);
    }

    // Creates a new token for a device by using the template: {username}/{UUID's first tokenLength chars}
    private String generateNewToken(String userName){
        String token = userName + "/" + UUID.randomUUID().toString().substring(0, tokenLength);
        while(thermostatRepository.findByToken(token) != null) token = userName + "/" + UUID.randomUUID().toString().substring(0, tokenLength); // Check if token is already in database. (low probability)
        return token;
    }
}
