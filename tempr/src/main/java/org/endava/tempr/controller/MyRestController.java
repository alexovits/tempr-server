package org.endava.tempr.controller;

import org.endava.tempr.model.UserEntry;
import org.endava.tempr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@EnableAutoConfiguration
@RestController
public class MyRestController {

    UserRepository userRepository;

    @Autowired
    public MyRestController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    List<UserEntry> getAll(){
        return userRepository.findAll();
    }

    @RequestMapping(value="/users/{id}", method = RequestMethod.GET)
    List<UserEntry> getUser(@PathVariable int id){
        return userRepository.findByuserId(id);
    }

    @RequestMapping(value="/insert/user/{id}", method = RequestMethod.GET)
    List<UserEntry> insertUser(@PathVariable int id){
        return userRepository.findByuserId(id);
    }

}