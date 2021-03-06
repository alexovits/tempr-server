package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.assembler.SensorLogAssembler;
import edu.endava.tempr.api.exception.HeatingCircuitNotFoundException;
import edu.endava.tempr.api.exception.OutOfHistogramRangeException;
import edu.endava.tempr.api.exception.SensorLogNotFoundException;
import edu.endava.tempr.api.exception.ThermostatNotFoundException;
import edu.endava.tempr.api.service.HeatingCircuitService;
import edu.endava.tempr.api.service.SensorLogService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.common.HeatingCircuitDto;
import edu.endava.tempr.common.SensorLogDto;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.SensorLog;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    private ThermostatService thermostatService;

    @Autowired
    private SensorLogAssembler sensorLogAssembler;

    /**
     * ••• Used by Hardware •••
     * Log the temperature on a specific Heating Circuit
     *
     * @param chipId      The unique ID of the sensor.
     * @param temperature Temperature value that shall be logged.
     * @return ResponseEntity containing the status of the request's action
     */
    @RequestMapping(value = "/thermostat/heatingcircuit/log/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSensorLog(@RequestHeader(value = "Authorization") String basicAuthHeader, @RequestParam("chipId") Long chipId, @RequestParam("temperature") Integer temperature) {
        LOG.info("Request to log for {} sensor the {} temperature {}", chipId, temperature, basicAuthHeader);
        HeatingCircuit heatingCircuit = null; // Fetch the heating circuit that has the sensor
        try {
            heatingCircuit = heatingCircuitService.findByChipId(chipId);
        } catch (HeatingCircuitNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        String decodedUserName = new String(Base64.decodeBase64(basicAuthHeader.split(" ")[1])).split(":")[0];
        // Validating that the hc belong to the user
        if (!heatingCircuitService.sensorBelongsToUser(decodedUserName, heatingCircuit)) {
            LOG.error("The chipId doesn't belong to the user");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        sensorLogService.create(temperature, heatingCircuit);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * ••• Used by UI •••
     * Creates a new Heating Circuit™
     *
     * @param heatingCircuitDto Must have the {name, thermostatToken, sensor={chipId}} defined.
     * @return ResponseEntity containing the status of the request's action
     */
    @RequestMapping(value = "/thermostat/heatingcircuit/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createHeatingCircuit(@RequestBody HeatingCircuitDto heatingCircuitDto) {
        LOG.info("Request to create HC {}", heatingCircuitDto);
        try {
            heatingCircuitService.create(heatingCircuitDto);
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * ••• Used by UI •••
     * Sets the desired temperature of a specific Heating Circuit
     *
     * @param heatingCircuitId   The internal ID of a Heating Circuit
     * @param desiredTemperature Temperature value that the desired temperature will be updated to.
     * @return ResponseEntity containing the status of the request's action
     */
    @RequestMapping(value = "/thermostat/heatingcircuit/desiredtemperature/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateDesiredTemperature(@RequestParam("heatingCircuitId") Long heatingCircuitId, @RequestParam("desiredTemperature") Integer desiredTemperature) {
        LOG.info("Request to set desired temperature of {} to {}", heatingCircuitId, desiredTemperature);
        try {
            heatingCircuitService.updateDesiredTemperature(heatingCircuitId, desiredTemperature);
        } catch (HeatingCircuitNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * ••• Used by UI •••
     * Returns the desired temperature of a specific Heating Circuit
     *
     * @param heatingCircuitId The internal ID of a Heating Circuit
     * @return ResponseEntity containing the status of the request's action and the desired temperature
     */
    @RequestMapping(value = "/thermostat/heatingcircuit/desiredtemperature/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDesiredTemperature(@RequestParam("heatingCircuitId") Long heatingCircuitId) {
        LOG.info("Request to get desired temperature of {}", heatingCircuitId);
        try {
            return new ResponseEntity(heatingCircuitService.getDesiredTemperature(heatingCircuitId), HttpStatus.OK);
        } catch (HeatingCircuitNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * ••• Used by UI •••
     * Returns the suggested temperature of a specific Heating Circuit
     *
     * @param heatingCircuitId The internal ID of a Heating Circuit
     * @return ResponseEntity containing the status of the request's action and the desired temperature
     */
    @RequestMapping(value = "/thermostat/heatingcircuit/suggestedtemperature/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSuggestedTemperature(@RequestParam("heatingCircuitId") Long heatingCircuitId) {
        LOG.info("Request to get suggested temperature of {}", heatingCircuitId);
        try {
            return new ResponseEntity<>(heatingCircuitService.getSuggestedTemperature(heatingCircuitId), HttpStatus.OK);
        } catch (OutOfHistogramRangeException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HeatingCircuitNotFoundException | SensorLogNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * ••• Used by UI •••
     * Sets the AIFlag of a specific Heating Circuit
     *
     * @param heatingCircuitId   The internal ID of a Heating Circuit
     * @param aiFlag Temperature value that the desired temperature will be updated to.
     * @return ResponseEntity containing the status of the request's action
     */
    @RequestMapping(value = "/thermostat/heatingcircuit/aiflag/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAiFlag(@RequestParam("heatingCircuitId") Long heatingCircuitId, @RequestParam("aiFlag") Boolean aiFlag) {
        LOG.info("Request to set AIFlag of {} to {}", heatingCircuitId, aiFlag);
        try {
            heatingCircuitService.updateAiFlag(heatingCircuitId, aiFlag);
        } catch (HeatingCircuitNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * ••• Used by UI •••
     * Returns the AIFlag temperature of a specific Heating Circuit
     *
     * @param heatingCircuitId The internal ID of a Heating Circuit
     * @return ResponseEntity containing the status of the request's action and the desired temperature
     */
    @RequestMapping(value = "/thermostat/heatingcircuit/aiflag/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAiFlag(@RequestParam("heatingCircuitId") Long heatingCircuitId) {
        LOG.info("Request to get AIFlag of {}", heatingCircuitId);
        try {
            return new ResponseEntity(heatingCircuitService.getAiFlag(heatingCircuitId), HttpStatus.OK);
        } catch (HeatingCircuitNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * ••• Used by UI •••
     * Returns the latest reported temperature of a heatingCircuit object, thus the most current one.
     *
     * @param heatingCircuitId The internal ID of a Heating Circuit
     * @return ResponseEntity containing the temperature as Integer and the Status
     */
    @RequestMapping(value = "/thermostat/heatingcircuit/temperature/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getLatestTempOfHeatingCircuit(@RequestParam("heatingCircuitId") Long heatingCircuitId) {
        LOG.info("Request for the latest temperature report: Heating Circuit™ ID -> {}", heatingCircuitId);
        try {
            return new ResponseEntity(sensorLogService.getLatestTemperature(heatingCircuitId), HttpStatus.OK);
        } catch (SensorLogNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * ••• Used by UI •••
     * Returns the logs from the last 7 days
     *
     * @return ResponseEntity containing the temperature as Integer and the Status
     */
    @RequestMapping(value = "/thermostat/heatingcircuit/history/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SensorLogDto>> getLastWeek(@RequestParam("heatingCircuitId") Long heatingCircuitId) {
        LOG.info("Request for the last 7 days temperature report: Heating Circuit™ ID -> {}", heatingCircuitId);
        List<SensorLogDto> responseList = new ArrayList<>();
        try {
            for (SensorLog sensorLog : sensorLogService.getLastWeeksLogs(heatingCircuitId)) {
                responseList.add(sensorLogAssembler.toDto(sensorLog));
            }
        } catch (SensorLogNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(responseList, HttpStatus.OK);
    }
}
