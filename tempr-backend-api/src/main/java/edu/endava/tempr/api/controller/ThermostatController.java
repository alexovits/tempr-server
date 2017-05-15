package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.assembler.ThermostatAssembler;
import edu.endava.tempr.api.exception.ThermostatAlreadyConfiguredException;
import edu.endava.tempr.api.exception.ThermostatNotFoundException;
import edu.endava.tempr.api.exception.UserNotFoundException;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zsoltszabo on 31/12/2016.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ThermostatController {

    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    @Autowired
    private ThermostatService thermostatService;

    @Autowired
    private ThermostatAssembler thermostatAssembler;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/thermostat/register/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThermostatDto> registerThermostat(@RequestBody ThermostatDto thermostatDto) {
        // Check if user that wants to create thermostat exists at all
        User user = null;
        try {
            user = userService.findOne(thermostatDto.getUserId());
            Thermostat newThermostat = thermostatService.createThermostat(user, thermostatAssembler.toEntity(thermostatDto));
            userService.updateUser(user);
            return new ResponseEntity<>(thermostatAssembler.toDto(newThermostat), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/thermostat/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThermostatDto> getThermostat(@RequestHeader(value = "Authorization") String basicAuthHeader) {
        String decodedUserName = new String(Base64.decodeBase64(basicAuthHeader.split(" ")[1])).split(":")[0];
        LOG.info("Request for thermostat from {}", decodedUserName);
        try {
            return new ResponseEntity<>(thermostatAssembler.toDto(thermostatService.findByUserName(decodedUserName)), HttpStatus.OK);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/thermostat/configure/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity configureThermostat(@RequestBody ThermostatDto thermostatDto) {
        try {
            thermostatService.configureThermostat(thermostatDto.getToken());
        } catch (ThermostatNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (ThermostatAlreadyConfiguredException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/thermostat/unconfigure/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThermostatDto> unConfigureThermostat(@RequestBody ThermostatDto thermostatDto) {
        try {
            thermostatService.unConfigureThermostat(thermostatDto.getToken());
        } catch (ThermostatNotFoundException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (ThermostatAlreadyConfiguredException e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}