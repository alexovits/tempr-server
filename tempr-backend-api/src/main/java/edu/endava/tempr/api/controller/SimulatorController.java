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
    private static final Logger LOG = LoggerFactory.getLogger(HeatingCircuitController.class);

    @Autowired
    SimulatorService simulatorService;

    @RequestMapping(value = "/simulator/log/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity simulateLog(@RequestParam("chipId") Long chipId, @RequestParam("temperature") Integer temperature) {
        LOG.info("Got the following parameters for logging: {} -> {}", chipId, temperature);
        simulatorService.logTemperature(temperature, chipId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/simulator/desiredtemp/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateDesiredTemperature(@RequestParam("chipId") Long chipId, @RequestParam("temperature") Integer temperature) {
        LOG.info("Got the following parameters for setting desired temperature: {} -> {}", chipId, temperature);
        simulatorService.setDesiredTemperature(temperature, chipId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/simulator/temperatures/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TemperaturesDto>> retrieveSimulatedLog(){
        return new ResponseEntity(simulatorService.getSimulatedLogList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/simulator/suggestedtemp/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateSuggestedTemperature(@RequestParam("chipId") Long chipId, @RequestParam("temperature") Integer temperature) {
        LOG.info("Got the following parameters for setting suggested temperature: {} -> {}", chipId, temperature);
        simulatorService.setSuggestedTemperature(temperature, chipId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/simulator/togglesuggestion/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateSuggestedTemperature(@RequestParam("suggestionFlag") Boolean suggestionFlag) {
        LOG.info("Got the following suggestion flag {}",suggestionFlag);
        simulatorService.setSuggestionFlag(suggestionFlag);
        return new ResponseEntity(HttpStatus.OK);
    }
}
