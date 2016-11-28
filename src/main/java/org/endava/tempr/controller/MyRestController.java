package org.endava.tempr.controller;

import org.apache.catalina.User;
import org.endava.tempr.model.UserEntry;
import org.endava.tempr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;


@EnableAutoConfiguration
@RestController
public class MyRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            value="/users/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserEntry>> getAllUsers(){

        List<UserEntry> users = userService.findAll();

        return new ResponseEntity<List<UserEntry>>(users,HttpStatus.OK);
    }

    @RequestMapping(
            value="/users/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserEntry> getUser(@PathVariable Long id){

        UserEntry user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<UserEntry>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UserEntry>(user, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/users/insert",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntry> createGreeting(@RequestBody UserEntry user) {

        UserEntry savedUser = userService.createUser(user);

        return new ResponseEntity<UserEntry>(savedUser, HttpStatus.CREATED);
    }

}

/*
* @CrossOrigin(*)
* LOG.info()
* */