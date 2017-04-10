package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.service.HeatingCircuitService;
import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.model.HeatingCircuit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by zsoltszabo on 4/8/17.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SensorController {

    private static final Logger LOG = LoggerFactory.getLogger(SensorController.class);

    @Autowired
    private HeatingCircuitService heatingCircuitService;

    @Autowired
    private SensorLogService sensorLogService;

    @RequestMapping(value = "/thermostat/heatingcircuit/log/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSensorLog(@RequestParam("chipId") Long chipId, @RequestParam("temperature") Integer temperature, @RequestParam("token") String token) {
        LOG.info("Got the following parameters for logging: {} -> {} -> {}", token, chipId, temperature);
        HeatingCircuit heatingCircuit;
        if((heatingCircuit = heatingCircuitService.findByChipId(chipId)) == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        sensorLogService.create(temperature, heatingCircuit);
        return new ResponseEntity(HttpStatus.OK);
    }

    // Isn't it problematic that anyone who has USER permission and knows the ID can change stuff?
    @RequestMapping(value = "/thermostat/heatingcircuit/desiredtemperature/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateDesiredTemperature(@RequestParam("chipId") Long chipId, @RequestParam("temperature") Integer temperature, @RequestParam("token") String token) {
        LOG.info("Request to set desired temperature of {} -> {} -> {}", token, chipId, temperature);
        HeatingCircuit heatingCircuit;
        if((heatingCircuit = heatingCircuitService.findByChipId(chipId)) == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        heatingCircuit.setDesiredTemperature(temperature);
        if(heatingCircuitService.update(heatingCircuit) == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
