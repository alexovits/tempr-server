package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
import edu.endava.tempr.repository.ThermostatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
@Service
public class ThermostatServiceBean implements ThermostatService {

    @Autowired
    ThermostatRepository thermostatRepository;

    private static final Logger logger = LoggerFactory.getLogger(ThermostatServiceBean.class);

    @Override
    public List<Thermostat> findAll() {
        return thermostatRepository.findAll();
    }

    private String generateNewToken(String userName){
        String token = userName + "/" + UUID.randomUUID().toString().substring(0, 5);
        // Check if token is already in database. (low probability)
        while(findOne(token) != null){
            token = userName + "/" + UUID.randomUUID().toString().substring(0, 5);
        }
        return token;
    }

    @Override
    public Thermostat findOne(String token) {
        return thermostatRepository.findByToken(token);
    }

    @Override
    public Thermostat createThermostat(User user, Thermostat thermostat) {
        Thermostat savedThermostat = null;
        // Create a new token for device by using the template: {user}/{UUID first n chars}
        thermostat.setToken(generateNewToken(user.getUsername()));
        thermostat.setUserId(user.getId());
        thermostat.setConfigured((short) 0);
        try {
            savedThermostat = thermostatRepository.save(thermostat);
        } catch(Exception ex){
            // Log it later
            ex.printStackTrace();
        }
        return savedThermostat;
    }

    @Override
    public Thermostat updateThermostat(Thermostat thermostat) {
        Thermostat thermostatToUpdate = thermostatRepository.findByToken(thermostat.getToken());
        if(thermostatToUpdate == null){
            logger.info("User with token: '{}' was not found!", thermostat.getToken());
            return null;
        }
        logger.info("User with token: '{}' was updated!", thermostat.getToken());
        return thermostatRepository.save(thermostat);
    }

    @Override
    public void deleteThermostat(Long id) {
        thermostatRepository.delete(id);
    }
}
