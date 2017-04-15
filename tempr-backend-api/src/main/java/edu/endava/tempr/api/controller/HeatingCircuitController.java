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
public class HeatingCircuitController {

    private static final Logger LOG = LoggerFactory.getLogger(HeatingCircuitController.class);

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

    /**
     * Returns the latest reported temperature of a heatingCircuit object, thus the most current one.
     *
     * @param chipId
     * @return ResponseEntity containing the temperature as Integer and the Status
     * */
    @RequestMapping(value = "/thermostat/heatingcircuit/desiredtemperature/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateDesiredTemperature(@RequestParam("chipId") Long chipId, @RequestParam("desiredTemperature") Integer desiredTemperature) {
        LOG.info("Request to set desired temperature of {} to {}", chipId, desiredTemperature);
        //heatingCircuitService.updateDesiredTemperature(chipId, desiredTemperature);
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

    /**
     * Returns the latest reported temperature of a heatingCircuit object, thus the most current one.
     *
     * @param heatingCircuitId
     * @return ResponseEntity containing the temperature as Integer and the Status
     * */
    @RequestMapping(value = "/thermostat/heatingcircuit/temperature/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getLatestTempOfHeatingCircuit(@RequestParam("heatingCircuitId") Long heatingCircuitId) {
        LOG.info("Request for the latet temperature report: Heating Circuit™ ID -> {}", heatingCircuitId);
        return new ResponseEntity(sensorLogService.getLatest(heatingCircuitId), HttpStatus.OK);
    }

    /*
    *
    * */
}
