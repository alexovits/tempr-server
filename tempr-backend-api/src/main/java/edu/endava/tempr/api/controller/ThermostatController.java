package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.assembler.ThermostatAssembler;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.common.ThermostatLogDto;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
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

    @Autowired
    private ThermostatService thermostatService;

    @Autowired
    private ThermostatAssembler thermostatAssembler;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/thermostat/register/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThermostatDto> registerThermostat(@RequestBody ThermostatDto thermostatDto) {
        User user = userService.findOne(thermostatDto.getUserId());

        // If user with id is not found
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Create new thermostat
        Thermostat newThermostat = thermostatService.createThermostat(user, thermostatAssembler.toEntity(thermostatDto));
        user.addThermostat(newThermostat);
        userService.updateUser(user);
        return new ResponseEntity<>(thermostatAssembler.toDto(newThermostat), HttpStatus.OK);
    }

    @RequestMapping(value = "/thermostat/configure/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThermostatDto> configureThermostat(@RequestBody ThermostatDto thermostatDto) {
        Thermostat thermostat = thermostatService.findOne(thermostatDto.getToken());

        // If thermostat with id is not found
        if(thermostat == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // If it's already configured inform client
        if(thermostat.getConfigured() == 1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Set it to configured
        thermostat.setConfigured((short) 1);
        thermostatService.updateThermostat(thermostat);

        return new ResponseEntity<>(thermostatAssembler.toDto(thermostat), HttpStatus.OK);
    }

    @RequestMapping(value = "/thermostat/unconfigure/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThermostatDto> unConfigureThermostat(@RequestBody ThermostatDto thermostatDto) {
        Thermostat thermostat = thermostatService.findOne(thermostatDto.getToken());

        // If thermostat with id is not found
        if(thermostat == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // If it's not configure yet
        if(thermostat.getConfigured() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // If it is configured -> unconfigure
        thermostat.setConfigured((short) 0);
        thermostatService.updateThermostat(thermostat);

        return new ResponseEntity<>(thermostatAssembler.toDto(thermostat), HttpStatus.OK);
    }

    @RequestMapping(value = "/thermostat/logtemp/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThermostatLogDto> logThermostatTemperature(@RequestBody ThermostatLogDto thermostatLogDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
