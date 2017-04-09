package edu.endava.tempr.api.assembler;

import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.common.HeatingCircuitDto;
import edu.endava.tempr.model.HeatingCircuit;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zsoltszabo on 4/4/17.
 */
public class HeatingCircuitAssembler implements Assembler<HeatingCircuitDto, HeatingCircuit>{

    @Autowired
    ThermostatService thermostatService;

    @Autowired
    HeatingSourceAssembler heatingSourceAssembler;

    @Override
    public HeatingCircuit toEntity(HeatingCircuitDto dto) {
        HeatingCircuit heatingCircuit = new HeatingCircuit();
        heatingCircuit.setName(dto.getName());
        heatingCircuit.setDesiredTemperature(dto.getDesiredTemperature());
        heatingCircuit.setHeatingSource(heatingSourceAssembler.toEntity(dto.getHeatingSourceDto()));
        heatingCircuit.setThermostat(thermostatService.findOne(dto.getThermostatToken()));
        heatingCircuit.setSuggestedTemperature(dto.getSuggestedTemperature());
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
        return heatingCircuitDto;
    }
}
