package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.assembler.ThermostatAssembler;
import edu.endava.tempr.api.assembler.UserAssembler;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.common.UserDto;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MyRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ThermostatService thermostatService;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private ThermostatAssembler thermostatAssembler;

    @RequestMapping(value = "/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtoList = users.stream().map(user -> userAssembler.toDto(user)).collect(Collectors.toList());
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userService.findOne(id);
        // If no user found with the given id
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userAssembler.toDto(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/login/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> loginUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/register/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        User user = userAssembler.toEntity(userDto);
        //Based on whether the user was created or not
        return (userService.createUser(user) != null) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/thermostat/register/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThermostatDto> registerDevice(@RequestBody ThermostatDto thermostatDto) {
        User user = userService.findOne(thermostatDto.getUserId());

        //!!!TO-DO validate if everything is okay with the input DTO!!!

        // If user with id is not found
        if(user == null){
            System.out.println("--------------Couldn't find user with id: "+thermostatDto.getUserId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("--------------Found user with id: "+thermostatDto.getUserId()+":"+thermostatDto.getName()+":"+thermostatDto.getToken());
        // Create new thermostat
        Thermostat newThermostat = thermostatService.createThermostat(thermostatAssembler.toEntity(thermostatDto));
        System.out.println("--------------New device created, with token: "+ newThermostat.getToken()+":"+newThermostat.getName());
        // Add it to the user's thermostat list
        userService.addThermostat(user,newThermostat);
        System.out.println("--------------New device added, with token: " + newThermostat.getToken());
        return new ResponseEntity<>(thermostatAssembler.toDto(newThermostat), HttpStatus.OK);
    }

}