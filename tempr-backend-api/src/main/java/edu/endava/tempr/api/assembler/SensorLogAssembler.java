package edu.endava.tempr.api.assembler;

import edu.endava.tempr.common.SensorLogDto;
import edu.endava.tempr.model.SensorLog;

/**
 * Created by zsoltszabo on 4/8/17.
 */
public class SensorLogAssembler implements Assembler<SensorLogDto,SensorLog> {

    @Override
    public SensorLog toEntity(SensorLogDto dto) {
        SensorLog sensorLog = new SensorLog();
        sensorLog.setLogTimeStamp(dto.getLogTimeStamp());
        sensorLog.setTemperature(dto.getTemperature());
        //Through a service module find the respective heating circuit
        //sensorLog.setHeatingCircuit(sensorLogService.findOne(dto.getHeatingCircuitId()));
        return null;
    }

    @Override
    public SensorLogDto toDto(SensorLog entity) {
        SensorLogDto sensorLogDto = new SensorLogDto();
        sensorLogDto.setTemperature(entity.getTemperature());
        sensorLogDto.setLogTimeStamp(entity.getLogTimeStamp());
        sensorLogDto.setHeatingCircuitId(entity.getHeatingCircuit().getId());
        return null;
    }
}
