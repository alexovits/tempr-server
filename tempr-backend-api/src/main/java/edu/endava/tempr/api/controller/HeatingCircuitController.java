package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.service.HeatingCircuitService;
import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.model.HeatingCircuit;
import org.apache.tomcat.util.codec.binary.Base64;
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

    /**
     * ••• Used by Hardware •••
     * Log the temperature on a specific Heating Circuit
     * @param sensorId The unique ID of the sensor.
     * @param temperature Temperature value that shall be logged.
     * @return ResponseEntity containing the status of the request's action
     * */
    @RequestMapping(value = "/thermostat/heatingcircuit/log/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSensorLog(@RequestHeader(value="Authorization") String basicAuthHeader, @RequestParam("sensorId") Long sensorId, @RequestParam("temperature") Integer temperature) {
        LOG.info("Request to log for {} sensor the {} temperature {}", sensorId, temperature, basicAuthHeader);
        HeatingCircuit heatingCircuit = heatingCircuitService.findByChipId(sensorId);
        // Decoding the second part of the Authorization header
        String decodedUserName = new String(Base64.decodeBase64(basicAuthHeader.split(" ")[1]));
        // Check if the Heating Circuit's Thermostat belongs to the same user that sent the request
        if(heatingCircuit.getThermostat().getUser().getUsername() != decodedUserName){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        sensorLogService.create(temperature, heatingCircuit);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * ••• Used by UI •••
     * Sets the desired temperature of a specific Heating Circuit
     * @param heatingCircuitId The internal ID of a Heating Circuit
     * @param desiredTemperature Temperature value that the desired temperature will be updated to.
     * @return ResponseEntity containing the status of the request's action
     * */
    @RequestMapping(value = "/thermostat/heatingcircuit/desiredtemperature/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity setDesiredTemperature(@RequestParam("heatingCircuitId") Long heatingCircuitId, @RequestParam("desiredTemperature") Integer desiredTemperature) {
        LOG.info("Request to set desired temperature of {} to {}", heatingCircuitId, desiredTemperature);
        heatingCircuitService.updateDesiredTemperature(heatingCircuitId, desiredTemperature);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * ••• Used by UI •••
     * Returns the desired temperature of a specific Heating Circuit
     * @param heatingCircuitId The internal ID of a Heating Circuit
     * @return ResponseEntity containing the status of the request's action and the desired temperature
     * */
    @RequestMapping(value = "/thermostat/heatingcircuit/desiredtemperature/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDesiredTemperature(@RequestParam("heatingCircuitId") Long heatingCircuitId) {
        LOG.info("Request to get desired temperature of {} to {}", heatingCircuitId);
        heatingCircuitService.getDesiredTemperature(heatingCircuitId);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * ••• Used by UI •••
     * Returns the latest reported temperature of a heatingCircuit object, thus the most current one.
     *
     * @param heatingCircuitId The internal ID of a Heating Circuit
     * @return ResponseEntity containing the temperature as Integer and the Status
     * */
    @RequestMapping(value = "/thermostat/heatingcircuit/temperature/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getLatestTempOfHeatingCircuit(@RequestParam("heatingCircuitId") Long heatingCircuitId) {
        LOG.info("Request for the latet temperature report: Heating Circuit™ ID -> {}", heatingCircuitId);
        return new ResponseEntity(sensorLogService.getLatestLog(heatingCircuitId), HttpStatus.OK);
    }

    /**
     * ••• Used by UI •••
     * Returns the latest temperature data from all of the sensors of a specific token
     *
     * @return ResponseEntity containing the temperature as Integer and the Status
     * */
    @RequestMapping(value = "/thermostat/temperatures/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTemperatures(@RequestParam("token") String thermostatToken) {
        LOG.info("Request for the temperature informations about {}", thermostatToken);
        //Implement get temp in thermostat service
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
    * UI knows ThermoId, userId, heatingCircuitId
    * */
}
