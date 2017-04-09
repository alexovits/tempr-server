package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.service.HeatingCircuitService;
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

    @RequestMapping(value = "/thermostat/sensor/log/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertSensorLog(@RequestParam("chipId") Long chipId, @RequestParam("temperature") Integer temperature) {
        LOG.info("Got the following parameters for logging: {} -> {}", chipId, temperature);
        return new ResponseEntity(HttpStatus.OK);
    }
}
