package edu.endava.tempr.api.assembler;

import edu.endava.tempr.common.SensorDto;
import edu.endava.tempr.model.Sensor;
import org.springframework.stereotype.Service;

/**
 * Created by zsoltszabo on 4/4/17.
 */
@Service
public class SensorAssembler implements Assembler<SensorDto,Sensor> {

    @Override
    public Sensor toEntity(SensorDto dto) {
        Sensor sensor = new Sensor();
        sensor.setSensorId(dto.getSensorId());
        return sensor;
    }

    @Override
    public SensorDto toDto(Sensor entity) {
        SensorDto sensorDto = new SensorDto();
        sensorDto.setSensorId(entity.getSensorId());
        return sensorDto;
    }
}
