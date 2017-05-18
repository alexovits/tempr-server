package edu.endava.tempr.api.assembler;

import edu.endava.tempr.api.exception.SensorNotFoundException;
import edu.endava.tempr.api.exception.ThermostatNotFoundException;
import edu.endava.tempr.api.service.SensorService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.common.HeatingCircuitDto;
import edu.endava.tempr.model.HeatingCircuit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zsoltszabo on 4/4/17.
 */
public class HeatingCircuitAssembler implements Assembler<HeatingCircuitDto, HeatingCircuit>{

    private static final Logger LOG = LoggerFactory.getLogger(HeatingCircuitAssembler.class);

    @Autowired
    ThermostatService thermostatService;

    @Autowired
    HeatingSourceAssembler heatingSourceAssembler;

    @Autowired
    SensorService sensorService;

    @Override
    public HeatingCircuit toEntity(HeatingCircuitDto dto) {
        HeatingCircuit heatingCircuit = new HeatingCircuit();
        heatingCircuit.setName(dto.getName());
        heatingCircuit.setDesiredTemperature(dto.getDesiredTemperature());
        heatingCircuit.setHeatingSource(heatingSourceAssembler.toEntity(dto.getHeatingSourceDto()));
        heatingCircuit.setSuggestedTemperature(dto.getSuggestedTemperature());
        try {
            heatingCircuit.setSensor(sensorService.findByChipId(dto.getSensorChipId()));
            heatingCircuit.setThermostat(thermostatService.findOne(dto.getThermostatToken()));
        } catch (ThermostatNotFoundException e) {
            LOG.error("Couldn't fully convert HC entity to object");
            LOG.error(e.getMessage());
        } catch (SensorNotFoundException e) {
            LOG.error("Couldn't fully convert HC entity to object");
            LOG.error(e.getMessage());
        }
        return heatingCircuit;
    }

    @Override
    public HeatingCircuitDto toDto(HeatingCircuit entity) {
        HeatingCircuitDto heatingCircuitDto = new HeatingCircuitDto();
        heatingCircuitDto.setName(entity.getName());
        heatingCircuitDto.setDesiredTemperature(entity.getDesiredTemperature());
        heatingCircuitDto.setHeatingSourceDto(heatingSourceAssembler.toDto(entity.getHeatingSource()));
        heatingCircuitDto.setThermostatToken(entity.getThermostat().getToken());
        heatingCircuitDto.setSuggestedTemperature(entity.getSuggestedTemperature());
        heatingCircuitDto.setSensorChipId(entity.getSensor().getChipId());
        return heatingCircuitDto;
    }
}
