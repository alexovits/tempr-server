package edu.endava.tempr.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zsoltszabo on 19/01/2017.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class VersionController {

    @Value("${server.version}")
    private String version;

    @RequestMapping(value = "/version", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getVersion() {
        return new ResponseEntity<>(version, HttpStatus.OK);
    }
}
