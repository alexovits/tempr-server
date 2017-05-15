package edu.endava.tempr.api.service.impl;

import edu.endava.tempr.api.exception.HeatingCircuitNotFoundException;
import edu.endava.tempr.api.exception.ThermostatNotFoundException;
import edu.endava.tempr.api.service.HeatingCircuitService;
import edu.endava.tempr.api.service.SensorService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.common.HeatingCircuitDto;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.repository.HeatingCircuitRepository;
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
    private ThermostatService thermostatService;

    @Autowired
    private SensorService sensorService;

    @Override
    public HeatingCircuit create(HeatingCircuitDto heatingCircuitDto) throws ThermostatNotFoundException {
        HeatingCircuit heatingCircuit = new HeatingCircuit();
        // Checks if all the necessary parameters are set
        if(heatingCircuitDto.getName() == null || heatingCircuitDto.getSensorChipId() == null || heatingCircuitDto.getThermostatToken() == null){
            throw new InvalidParameterException("Name, chipID and token needed for the Heating Circuit registration");
        }
        // Checks if a thermostat with the given token exists
        Thermostat ownerThermostat = thermostatService.findOne(heatingCircuitDto.getThermostatToken());
        // Sets only the obligatory default attributes
        heatingCircuit.setName(heatingCircuitDto.getName());
        heatingCircuit.setThermostat(ownerThermostat);
        heatingCircuit.setAiFlag(false); //By default when creating new HC flag is false
        heatingCircuit.setSensor(sensorService.create(heatingCircuitDto.getSensorChipId()));
        HeatingCircuit savedHeatingCircuit = heatingCircuitRepository.save(heatingCircuit);
        LOG.info("Created Heating Circuit™ with id: {} for thermostat with token: {}", savedHeatingCircuit.getId(), savedHeatingCircuit.getThermostat().getToken());
        return savedHeatingCircuit;
    }

    @Override
    public HeatingCircuit findByChipId(long chipId) throws HeatingCircuitNotFoundException {
        HeatingCircuit heatingCircuit = heatingCircuitRepository.findBySensorChipId(chipId);
        if(heatingCircuit == null) throw new HeatingCircuitNotFoundException(String.format("Couldn't find Heating Circuit™ with chip ID %1$d", chipId));
        return heatingCircuit;
    }

    @Override
    public HeatingCircuit findOne(long heatingCircuitId) throws HeatingCircuitNotFoundException {
        HeatingCircuit heatingCircuit = heatingCircuitRepository.findOne(heatingCircuitId);
        if (heatingCircuit == null) throw new HeatingCircuitNotFoundException(String.format("Couldn't find Heating Circuit™ with ID %1$d", heatingCircuitId));
        return heatingCircuit;
    }

    @Override
    public HeatingCircuit update(HeatingCircuit heatingCircuit) throws HeatingCircuitNotFoundException {
        HeatingCircuit checkHc = findOne(heatingCircuit.getId());
        return heatingCircuitRepository.save(findOne(heatingCircuit.getId()));
    }

    @Override
    public Integer getDesiredTemperature(long heatingCircuitId) throws HeatingCircuitNotFoundException {
        return findOne(heatingCircuitId).getDesiredTemperature();
}

    @Override
    public void updateDesiredTemperature(long heatingCircuitId, int desiredTemperature) throws HeatingCircuitNotFoundException {
        HeatingCircuit heatingCircuit = findOne(heatingCircuitId);
        heatingCircuit.setDesiredTemperature(desiredTemperature);
        update(heatingCircuit);
    }

    @Override
    public Boolean getAiFlag(long heatingCircuitId) throws HeatingCircuitNotFoundException {
        return findOne(heatingCircuitId).getAiFlag();
    }

    @Override
    public void updateAiFlag(long heatingCircuitId, boolean aiFlag) throws HeatingCircuitNotFoundException {
        HeatingCircuit heatingCircuit = findOne(heatingCircuitId);
        heatingCircuit.setAiFlag(aiFlag);
        update(heatingCircuit);
    }

    @Override
    public Integer getSuggestedTemperature(long heatingCircuitId) throws HeatingCircuitNotFoundException {
        return findOne(heatingCircuitId).getSuggestedTemperature();
    }

    @Override
    public void updateSuggestedTemperature(long heatingCircuitId, int suggestedTemperature) throws HeatingCircuitNotFoundException {
        HeatingCircuit heatingCircuit = findOne(heatingCircuitId);
        heatingCircuit.setSuggestedTemperature(suggestedTemperature);
        update(heatingCircuit);
    }

    @Override
    public boolean sensorBelongsToUser(String userName, HeatingCircuit heatingCircuit) {
        LOG.info("Validating if user: {} owns the Heating Circuit with id: {}", userName, heatingCircuit.getId());
        // Check if the Heating Circuit's Thermostat belongs to the same user that sent the request
        if(!heatingCircuit.getThermostat().getUser().getUsername().equals(userName)) {
            return false;
        }
        return true;
    }
}
