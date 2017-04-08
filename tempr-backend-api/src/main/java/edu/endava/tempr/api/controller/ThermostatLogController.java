package edu.endava.tempr.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zsoltszabo on 10/01/2017.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ThermostatLogController {
/*
    private static final Logger LOG = LoggerFactory.getLogger(ThermostatLogController.class);

    @Autowired
    private ThermostatLogAssembler thermostatLogAssembler;

    @Autowired
    private ThermostatLogService thermostatLogService;

    @Autowired
    private ThermostatService thermostatService;

    @RequestMapping(value = "/thermostat/heatingcircuit/log/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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
        Thermostat targetThermostat = thermostatService.findOne(thermostatId);
        Integer desiredTemperature;
        if(targetThermostat == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if ((desiredTemperature = targetThermostat.getDesiredTemperature()) == null){
            // If the desired temoerature is not set yet
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(desiredTemperature, HttpStatus.OK);
    }

// Handling POST to set the desired temperature set for a specific thermostat
    @RequestMapping(value = "/thermostat/desiredtemp/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getDesiredTemperature(@RequestBody ThermostatDto thermostatDto){
        Thermostat currentThermostat;
        // If there's no token at all, OR if it's non-existent return error OR temperature is not specified
        if(thermostatDto.getToken() == null || (currentThermostat = thermostatService.findOne(thermostatDto.getToken())) == null || thermostatDto.getDesiredTemperature() == null){
            return new ResponseEntity(1, HttpStatus.BAD_REQUEST);
        }

        currentThermostat.setDesiredTemperature(thermostatDto.getDesiredTemperature());
        thermostatService.updateThermostat(currentThermostat);
        return new ResponseEntity(2, HttpStatus.OK);
    }
    */
    //TODO:
    /**
        - temperatures GET -> returns of every HC the desired, suggested, current,
        - /desiredTemp POST -> sets desired temperature of given HC
        - /log -> logs HC's temp

        - HW endpoints:
            - /log
            - temperatures GET

        - UI endpoints:
            - /desiredTemp POST (Later)
            - /temperatures GET
            - ON/OFF ML
            - /loghistory -> sensor last x days temp (Later)
            - /heatingcircuit/register (Later)

        - SUMILATOR: (/simulator indicates in-memory db usage)
            - GET /simulator/temperatures
            - /simulator/log
            - /simulator/thermostat/register (Later)
            - /simulator/heatingcircuit/register (Later)
            - /simulator/reset (Later)
    **/



}
