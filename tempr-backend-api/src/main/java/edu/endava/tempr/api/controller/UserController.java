package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.assembler.UserAssembler;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.common.ThermostatDto;
import edu.endava.tempr.common.UserDto;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zsoltszabo on 31/12/2016.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

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
    public ResponseEntity<UserDto> loginUser(@RequestHeader(value = "Authorization") String basicAuthHeader) {
        String decodedUserName = new String(Base64.decodeBase64(basicAuthHeader.split(" ")[1])).split(":")[0];
        User user = userService.findByName(decodedUserName);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(""); // For safety
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

    @RequestMapping(value = "/user/thermostatList/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThermostatDto> getThermostatList(@RequestBody UserDto userDto) {
        User user = userService.findOne(userDto.getId());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //LOG.info("Sending back token: {}", user.getThermostat().getToken());
        //return new ResponseEntity<>(userAssembler.toDto(user).getThermostatDto(), HttpStatus.OK);
        return null;
    }
}
