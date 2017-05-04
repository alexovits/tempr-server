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
        heatingCircuit.setAiFlag(false); //By default when creating new HC flag is false
        HeatingCircuit savedHeatingCircuit = heatingCircuitRepository.save(heatingCircuit);
        LOG.info("Created Heating Circuit™ with id: {} for thermostat with token: {}", savedHeatingCircuit.getId(), thermostatToken);
        // Q: kell ez? it's unidir
        ownerThermostat.addHeatingCircuit(savedHeatingCircuit);
        thermostatRepository.save(ownerThermostat);
        return savedHeatingCircuit;
    }

    @Override
    public HeatingCircuit findByChipId(long chipId) {
        return heatingCircuitRepository.findBySensorChipId(chipId);
    }

    @Override
    public HeatingCircuit findOne(long heatingCircuitId) {
        return heatingCircuitRepository.findOne(heatingCircuitId);
    }

    @Override
    public HeatingCircuit update(HeatingCircuit heatingCircuit) {
        HeatingCircuit updateHeatingCircuit = heatingCircuitRepository.findOne(heatingCircuit.getId());
        if(updateHeatingCircuit == null){
            LOG.error("No Heating Circuit™ found with this ID: {}", updateHeatingCircuit.getId());
            throw new InvalidParameterException(String.format("No Heating Circuit™ found with this ID %1$d",heatingCircuit.getId()));
        }
        return heatingCircuitRepository.save(heatingCircuit);
    }

    @Override
    public Integer getDesiredTemperature(long heatingCircuitId) {
        //TODO --> Check if there's no valid hcID and throw exception (Maybe put it inside findOne)
        HeatingCircuit heatingCircuit = findOne(heatingCircuitId);
        return heatingCircuit.getDesiredTemperature();
    }

    @Override
    public void updateDesiredTemperature(long heatingCircuitId, int desiredTemperature) {
        HeatingCircuit heatingCircuit = findOne(heatingCircuitId);
        heatingCircuit.setDesiredTemperature(desiredTemperature);
        update(heatingCircuit);
    }

    @Override
    public Boolean getAiFlag(long heatingCircuitId) {
        HeatingCircuit heatingCircuit = findOne(heatingCircuitId);
        return heatingCircuit.getAiFlag();
    }

    @Override
    public void updateAiFlag(long heatingCircuitId, boolean aiFlag) {
        HeatingCircuit heatingCircuit = findOne(heatingCircuitId);
        heatingCircuit.setAiFlag(aiFlag);
        update(heatingCircuit);
    }

    @Override
    public Integer getSuggestedTemperature(long heatingCircuitId) {
        HeatingCircuit heatingCircuit = findOne(heatingCircuitId);
        return heatingCircuit.getSuggestedTemperature();
    }

    @Override
    public void updateSuggestedTemperature(long heatingCircuitId, int suggestedTemperature) {
        HeatingCircuit heatingCircuit = findOne(heatingCircuitId);
        heatingCircuit.setSuggestedTemperature(suggestedTemperature);
        update(heatingCircuit);
    }
}
