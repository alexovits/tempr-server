package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.assembler.ThermostatLogAssembler;
import edu.endava.tempr.api.service.ThermostatLogService;
import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.common.ThermostatLogDto;
import edu.endava.tempr.model.ThermostatLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zsoltszabo on 10/01/2017.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ThermostatLogController {

    @Autowired
    private ThermostatLogAssembler thermostatLogAssembler;

    @Autowired
    private ThermostatLogService thermostatLogService;

    @RequestMapping(value = "/thermostat/logtemp/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logThermostatTemperature(@RequestBody ThermostatLogDto thermostatLogDto) {
        if(thermostatLogDto.getToken() == null || thermostatLogDto.getLogTimeStamp() == null || thermostatLogDto.getIntTemp() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ThermostatLog thermostatLog = thermostatLogAssembler.toEntity(thermostatLogDto);
        thermostatLogService.create(thermostatLog);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/thermostat/latestlog/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getLatestLog(@RequestBody ThermostatDto thermostatDto) {
        /*if(thermostatLogDto.getToken() == null || thermostatLogDto.getLogTimeStamp() == null || thermostatLogDto.getIntTemp() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ThermostatLog thermostatLog = thermostatLogAssembler.toEntity(thermostatLogDto);
        thermostatLogService.create(thermostatLog);
        return new ResponseEntity(HttpStatus.OK);*/
        return null;
    }

    @RequestMapping(value = "/thermostat/loghistory/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getLogHistory(@RequestBody ThermostatDto thermostatDto) {
        /*if(thermostatLogDto.getToken() == null || thermostatLogDto.getLogTimeStamp() == null || thermostatLogDto.getIntTemp() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ThermostatLog thermostatLog = thermostatLogAssembler.toEntity(thermostatLogDto);
        thermostatLogService.create(thermostatLog);
        return new ResponseEntity(HttpStatus.OK);*/
        return null;
    }

}
