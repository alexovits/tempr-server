package edu.endava.tempr.api.assembler;

import edu.endava.tempr.api.exception.HeatingCircuitNotFoundException;
import edu.endava.tempr.api.service.HeatingCircuitService;
import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.common.SensorLogDto;
import edu.endava.tempr.model.SensorLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zsoltszabo on 4/8/17.
 */
@Service
public class SensorLogAssembler implements Assembler<SensorLogDto,SensorLog> {

    private static final Logger LOG = LoggerFactory.getLogger(SensorLogAssembler.class);

    @Autowired
    HeatingCircuitService heatingCircuitService;

    @Override
    public SensorLog toEntity(SensorLogDto dto) {
        SensorLog sensorLog = new SensorLog();
        sensorLog.setLogTimeStamp(dto.getLogTimeStamp());
        sensorLog.setTemperature(dto.getTemperature());
        try {
            sensorLog.setHeatingCircuit(heatingCircuitService.findOne(dto.getHeatingCircuitId()));
        } catch (HeatingCircuitNotFoundException e) {
            LOG.error("Couldn't fully convert HC entity to object");
            LOG.error(e.getMessage());
        }
        return sensorLog;
    }

    @Override
    public SensorLogDto toDto(SensorLog entity) {
        SensorLogDto sensorLogDto = new SensorLogDto();
        sensorLogDto.setTemperature(entity.getTemperature());
        sensorLogDto.setLogTimeStamp(entity.getLogTimeStamp());
        sensorLogDto.setHeatingCircuitId(entity.getHeatingCircuit().getId());
        return sensorLogDto;
    }
}
