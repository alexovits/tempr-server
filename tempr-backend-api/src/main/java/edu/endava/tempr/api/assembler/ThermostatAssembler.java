package edu.endava.tempr.api.assembler;


import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.model.Thermostat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
@Service
public class ThermostatAssembler implements Assembler<ThermostatDto, Thermostat> {

    @Autowired
    private ThermostatLogAssembler thermostatLogAssembler;

    @Autowired
    private UserService userService;

    @Override
    public Thermostat toEntity(ThermostatDto dto) {
        Thermostat thermostat = new Thermostat();
        thermostat.setName(dto.getName());
        thermostat.setToken(dto.getToken());
        thermostat.setConfigured(dto.getConfigured());
        thermostat.setUser(userService.findOne(dto.getUserId()));
        thermostat.setDesiredTemperature(dto.getDesiredTemperature());
        return thermostat;
    }

    @Override
    public ThermostatDto toDto(Thermostat entity) {
        ThermostatDto thermostatDto = new ThermostatDto();
        thermostatDto.setName(entity.getName());
        thermostatDto.setToken(entity.getToken());
        thermostatDto.setConfigured(entity.getConfigured());
        thermostatDto.setUserId(entity.getUser().getId());
        thermostatDto.setDesiredTemperature(entity.getDesiredTemperature());
        return thermostatDto;
    }
}
