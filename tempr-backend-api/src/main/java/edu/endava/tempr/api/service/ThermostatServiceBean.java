package edu.endava.tempr.api.service;

import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
import edu.endava.tempr.repository.ThermostatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
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
        try {
            savedThermostat = thermostatRepository.save(thermostat);
        } catch(Exception ex){
            //Log it later
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
