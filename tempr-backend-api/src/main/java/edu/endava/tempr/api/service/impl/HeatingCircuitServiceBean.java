package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.service.HeatingCircuitService;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.repository.HeatingCircuitRepository;
import edu.endava.tempr.repository.ThermostatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

/**
 * Created by zsoltszabo on 4/9/17.
 */
@Service
public class HeatingCircuitServiceBean implements HeatingCircuitService {

    private static final Logger LOG = LoggerFactory.getLogger(ThermostatServiceBean.class);

    @Autowired
    private HeatingCircuitRepository heatingCircuitRepository;

    @Autowired
    private ThermostatRepository thermostatRepository;

    @Override
    public HeatingCircuit create(HeatingCircuit heatingCircuit, String thermostatToken) {
        Thermostat ownerThermostat;
        if((ownerThermostat = thermostatRepository.findByToken(thermostatToken)) == null){
            throw new InvalidParameterException("Couldn't find Thermostat with the token: " + thermostatToken);
        }
        heatingCircuit.setThermostat(ownerThermostat);
        HeatingCircuit savedHeatingCircuit = heatingCircuitRepository.save(heatingCircuit);
        LOG.info("Created Heating Circuit™ with id: {} for thermostat with token: {}", savedHeatingCircuit.getId(), thermostatToken);
        // Q: kell ez? it's unidir
        ownerThermostat.addHeatingCircuit(savedHeatingCircuit);
        thermostatRepository.save(ownerThermostat);
        return savedHeatingCircuit;
    }

    @Override
    public HeatingCircuit findByChipId(Long chipId) {
        return heatingCircuitRepository.findBySensorChipId(chipId);
    }

    //Isn't this a bit redundant? Should we have dedicated functions?
    // Maybe if the update would check on whether the object has id/chipId it can search for it so only 1 query is executed
    @Override
    public HeatingCircuit update(HeatingCircuit heatingCircuit) {
        HeatingCircuit updateHeatingCircuit = heatingCircuitRepository.findOne(heatingCircuit.getId());
        if(updateHeatingCircuit == null){
            LOG.error("No Heating Circuit™ found with this ID: {}", updateHeatingCircuit.getId());
            return null;
        }
        return heatingCircuitRepository.save(heatingCircuit);
    }
}
