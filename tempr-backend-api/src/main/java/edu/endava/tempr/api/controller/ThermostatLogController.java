package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.assembler.ThermostatLogAssembler;
import edu.endava.tempr.api.service.ThermostatLogService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.common.ThermostatLogDto;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.ThermostatLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsoltszabo on 10/01/2017.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ThermostatLogController {

    private static final Logger LOG = LoggerFactory.getLogger(ThermostatLogController.class);

    @Autowired
    private ThermostatLogAssembler thermostatLogAssembler;

    @Autowired
    private ThermostatLogService thermostatLogService;

    @Autowired
    private ThermostatService thermostatService;

    @RequestMapping(value = "/thermostat/log/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logThermostatTemperature(@RequestBody ThermostatLogDto thermostatLogDto) {
        if(thermostatLogDto.getToken() == null || thermostatLogDto.getIntTemp() == null || thermostatService.findOne(thermostatLogDto.getToken()) == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        ThermostatLog thermostatLog = thermostatLogAssembler.toEntity(thermostatLogDto);
        thermostatLog.setLogTimeStamp(LocalDateTime.now());
        thermostatLogService.create(thermostatLog);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/thermostat/latestlog/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThermostatLogDto> getLatestLog(@RequestBody ThermostatDto thermostatDto) {
        // If there's no token at all, or if it's non-existent return error
        if(thermostatDto.getToken() == null || thermostatService.findOne(thermostatDto.getToken()) == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        // Otherwise return the latest temperature
        // Error arises here when there's no logs at all
        ThermostatLog thermostatLog = thermostatLogService.getLatest(thermostatDto.getToken());
        if(thermostatLog == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(thermostatLogAssembler.toDto(thermostatLog),HttpStatus.OK);
    }

    @RequestMapping(value = "/thermostat/loghistory/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ThermostatLogDto>> getLogHistory(@RequestBody ThermostatDto thermostatDto) {
        // If there's no token at all, or if it's non-existent return error
        if(thermostatDto.getToken() == null || thermostatService.findOne(thermostatDto.getToken()) == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<ThermostatLogDto> fetchedLogs = new ArrayList<>();
        LOG.info("Fetched the next thermostatlogs: ");
        for(ThermostatLog t: thermostatLogService.getLastTenDays(thermostatDto.getToken())){
            LOG.info(t.toString());
            fetchedLogs.add(thermostatLogAssembler.toDto(t));
        }

        return new ResponseEntity<>(fetchedLogs, HttpStatus.OK);
    }

    // Handling GET to the actual desired temperature set for a specific thermostat
    @RequestMapping(value = "/thermostat/desiredtemp/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getDesiredTemperature(@RequestParam("thermostatId") String thermostatId){
        Thermostat targetTermostat = thermostatService.findOne(thermostatId);
        Integer desiredTemperature;
        if(targetTermostat == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if ((desiredTemperature = targetTermostat.getDesiredTemperature()) == null){
            // If the desired temoerature is not set yet
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(desiredTemperature, HttpStatus.OK);
    }


    // Handling POST to set the desired temperature set for a specific thermostat
    /*@RequestMapping(value = "/thermostat/desiredtemp/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getDesiredTemperature(@RequestBody ThermostatDto thermostatDto){
        // If there's no token at all, OR if it's non-existent return error OR temperature is not specified
        if(thermostatDto.getToken() == null || thermostatService.findOne(thermostatDto.getToken()) == null || thermostatDto.get){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }


    }*/

}
