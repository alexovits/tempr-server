package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.service.SimulatorService;
import edu.endava.tempr.common.TemperaturesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zsoltszabo on 4/9/17.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SimulatorController {
    private static final Logger LOG = LoggerFactory.getLogger(SensorController.class);

    @Autowired
    SimulatorService simulatorService;

    @RequestMapping(value = "/simulator/log/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity simulateLog(@RequestParam("chipId") Long chipId, @RequestParam("temperature") Integer temperature) {
        LOG.info("Got the following parameters for logging: {} -> {}", chipId, temperature);
        simulatorService.logTemperature(temperature, chipId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/simulator/temperatures/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TemperaturesDto>> retrieveSimulatedLog(){
        return new ResponseEntity(simulatorService.getSimulatedLogList(), HttpStatus.OK);
    }


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
