package edu.endava.tempr.api.assembler;


import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.model.Thermostat;
import org.springframework.stereotype.Service;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
@Service
public class ThermostatAssembler implements Assembler<ThermostatDto, Thermostat> {

    @Override
    public Thermostat toEntity(ThermostatDto dto) {
        Thermostat thermostat = new Thermostat();
        thermostat.setName(dto.getName());
        thermostat.setToken(dto.getToken());
        return thermostat;
    }

    @Override
    public ThermostatDto toDto(Thermostat entity) {
        ThermostatDto thermostatDto = new ThermostatDto();
        thermostatDto.setName(entity.getName());
        thermostatDto.setToken(entity.getToken());
        return thermostatDto;
    }
}
