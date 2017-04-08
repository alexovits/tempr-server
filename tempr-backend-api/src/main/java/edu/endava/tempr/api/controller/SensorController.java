package edu.endava.tempr.api.controller;

import edu.endava.tempr.common.SensorLogDto;
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
    @RequestMapping(value = "/thermostat/sensor/log/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity configureThermostat(@RequestBody SensorLogDto sensorLogDto) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
