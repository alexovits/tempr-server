package edu.endava.tempr.api.service;

import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.repository.ThermostatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
@Service
public class ThermostatServiceBean implements ThermostatService{

    @Autowired
    ThermostatRepository thermostatRepository;

    @Override
    public List<Thermostat> findAll() {
        return thermostatRepository.findAll();
    }

    @Override
    public Thermostat findOne(String token) {
        return thermostatRepository.findByToken(token);
    }

    @Override
    public Thermostat createThermostat(Thermostat thermostat) {
        Thermostat savedThermostat = null;
        // Create a new random UUID token for the thermostat
        thermostat.setToken(UUID.randomUUID().toString());
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
        return null;
    }

    @Override
    public void deleteThermostat(Long id) {
        thermostatRepository.delete(id);
    }
}
