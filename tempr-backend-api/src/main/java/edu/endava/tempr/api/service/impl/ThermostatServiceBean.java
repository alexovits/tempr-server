package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.common.TemperaturesDto;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
import edu.endava.tempr.repository.ThermostatRepository;
import edu.endava.tempr.repository.UserRepository;
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
    UserRepository userRepository;

    @Autowired
    SensorLogService sensorLogService;

    @Override
    public List<Thermostat> findAll() {
        LOG.info("Looking for all thermostats");
        return thermostatRepository.findAll();
    }

    // Creates a new token for a device by using the template: {username}/{UUID's first tokenLength chars}
    private String generateNewToken(String userName){
        String token = userName + "/" + UUID.randomUUID().toString().substring(0, tokenLength);
        // Check if token is already in database. (low probability)
        while(findOne(token) != null){
            token = userName + "/" + UUID.randomUUID().toString().substring(0, tokenLength);
        }
        return token;
    }

    @Override
    public Thermostat findOne(String token) {
        LOG.info("Looking for thermostat with token: '{}'", token);
        return thermostatRepository.findByToken(token);
    }

    @Override
    public Thermostat createThermostat(User user, Thermostat thermostat) {
        Thermostat savedThermostat = null;
        thermostat.setToken(generateNewToken(user.getUsername()));
        thermostat.setConfigured((short) 0);
        thermostat.setUser(user);
        try {
            savedThermostat = thermostatRepository.save(thermostat);
        } catch(Exception ex){
            LOG.error("Error occured when saving new thermostat for user with the id '{}'",user.getId(), ex);
        }
        LOG.info("Created thermostat with token: '{}'", savedThermostat.getToken());
        LOG.info("Created thermostat: '{}'", savedThermostat.toString());

        // Inserts save thermostat into user's list
        // user.addThermostat(savedThermostat);
        //userRepository.save(user);

        return savedThermostat;
    }

    @Override
    public Thermostat updateThermostat(Thermostat thermostat) {
        Thermostat thermostatToUpdate = thermostatRepository.findByToken(thermostat.getToken());
        if(thermostatToUpdate == null){
            LOG.info("Thermostat with token: '{}' was not found!", thermostat.getToken());
            //TODO: Throw exception
            return null;
        }
        LOG.info("Thermostat with token: '{}' was updated!", thermostat.getToken());
        return thermostatRepository.save(thermostat);
    }

    @Override
    public List getTemperatures(String thermostatToken) {
        List temperatureList = new ArrayList<TemperaturesDto>();
        Thermostat thermostat = thermostatRepository.findByToken(thermostatToken);

        if(thermostat == null){
            LOG.info("Thermostat with token: '{}' was not found!", thermostat.getToken());
            throw new RuntimeException("Invalid Token");
        }

        thermostat.getHeatingCircuitList().forEach(
                hc -> temperatureList.add(new TemperaturesDto(sensorLogService.getLatestTemperature(hc.getId()), hc.getSuggestedTemperature(), hc.getDesiredTemperature(), hc.getId(), hc.getAiFlag()))
        );
        return temperatureList;
    }

    @Override
    public void deleteThermostat(Long id) {
        LOG.info("Deleting thermostat with id: '{}'", id);
        thermostatRepository.delete(id);
    }
}
