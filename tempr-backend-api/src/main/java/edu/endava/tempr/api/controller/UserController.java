package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.assembler.UserAssembler;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.common.UserDto;
import edu.endava.tempr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zsoltszabo on 31/12/2016.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private ThermostatService thermostatService;

    @RequestMapping(value = "/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtoList = users.stream().map(user -> userAssembler.toDto(user)).collect(Collectors.toList());
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/login/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto userDto) {
        User user = userService.findByName(userDto.getUsername());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userAssembler.toDto(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/register/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        User user = userAssembler.toEntity(userDto);

        //Based on whether the user was created or not
        return (userService.createUser(user) != null) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Only exists for occasional debugging purposes
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userService.findOne(id);

        // If no user found with the given id
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userAssembler.toDto(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/thermostatList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ThermostatDto>> getThermostatList(@RequestBody UserDto userDto) {
        User user = userService.findOne(userDto.getId());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userAssembler.toDto(user).getThermostatDtoList(), HttpStatus.OK);
    }
}
