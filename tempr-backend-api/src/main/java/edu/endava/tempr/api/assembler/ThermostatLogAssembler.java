package edu.endava.tempr.api.assembler;

import edu.endava.tempr.common.ThermostatLogDto;
import edu.endava.tempr.model.ThermostatLog;
import org.springframework.stereotype.Service;

/**
 * Created by zsoltszabo on 02/01/2017.
 */
@Service
public class ThermostatLogAssembler implements Assembler<ThermostatLogDto, ThermostatLog>{

    @Override
    public ThermostatLog toEntity(ThermostatLogDto dto) {
        ThermostatLog thermostatLog = new ThermostatLog();
        thermostatLog.setToken(dto.getToken());
        thermostatLog.setExtTemp(dto.getExtTemp());
        thermostatLog.setIntTemp(dto.getIntTemp());
        thermostatLog.setLogTimeStamp(dto.getLogTimeStamp());
        return thermostatLog;
    }

    @Override
    public ThermostatLogDto toDto(ThermostatLog entity) {
        ThermostatLogDto thermostatLogDto = new ThermostatLogDto();
        thermostatLogDto.setToken(entity.getToken());
        thermostatLogDto.setExtTemp(entity.getExtTemp());
        thermostatLogDto.setIntTemp(entity.getIntTemp());
        thermostatLogDto.setLogTimeStamp(entity.getLogTimeStamp());
        return thermostatLogDto;
    }
}
