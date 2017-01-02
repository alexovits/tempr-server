package edu.endava.tempr.api.assembler;


import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.common.ThermostatLogDto;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.ThermostatLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
@Service
public class ThermostatAssembler implements Assembler<ThermostatDto, Thermostat> {

    @Autowired
    private ThermostatLogAssembler thermostatLogAssembler;

    @Override
    public Thermostat toEntity(ThermostatDto dto) {
        Thermostat thermostat = new Thermostat();
        thermostat.setName(dto.getName());
        thermostat.setToken(dto.getToken());
        thermostat.setConfigured(dto.getConfigured());
        thermostat.setUserId(dto.getUserId());
        for(ThermostatLogDto t: dto.getThermostatLogDtoList()){
            thermostat.getThermostatLogList().add(thermostatLogAssembler.toEntity(t));
        }
        return thermostat;
    }

    @Override
    public ThermostatDto toDto(Thermostat entity) {
        ThermostatDto thermostatDto = new ThermostatDto();
        thermostatDto.setName(entity.getName());
        thermostatDto.setToken(entity.getToken());
        thermostatDto.setConfigured(entity.getConfigured());
        thermostatDto.setUserId(entity.getUserId());
        for(ThermostatLog t: entity.getThermostatLogList()){
            thermostatDto.getThermostatLogDtoList().add(thermostatLogAssembler.toDto(t));
        }
        return thermostatDto;
    }
}
