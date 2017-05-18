package edu.endava.tempr.api.assembler;


import edu.endava.tempr.api.exception.UserNotFoundException;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.model.Thermostat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
@Service
public class ThermostatAssembler implements Assembler<ThermostatDto, Thermostat> {

    private static final Logger LOG = LoggerFactory.getLogger(ThermostatAssembler.class);;

    @Autowired
    private UserService userService;

    @Override
    public Thermostat toEntity(ThermostatDto dto) {
        Thermostat thermostat = new Thermostat();
        thermostat.setName(dto.getName());
        thermostat.setToken(dto.getToken());
        thermostat.setConfigured(dto.getConfigured());
        try {
            thermostat.setUser(userService.findOne(dto.getUserId()));
        } catch (UserNotFoundException e) {
            LOG.error("Couldn't fully convert Thermostat entity to object");
            LOG.error(e.getMessage());
        }
        return thermostat;
    }

    @Override
    public ThermostatDto toDto(Thermostat entity) {
        ThermostatDto thermostatDto = new ThermostatDto();
        thermostatDto.setName(entity.getName());
        thermostatDto.setToken(entity.getToken());
        thermostatDto.setConfigured(entity.getConfigured());
        thermostatDto.setUserId(entity.getUser().getId());
        return thermostatDto;
    }
}
