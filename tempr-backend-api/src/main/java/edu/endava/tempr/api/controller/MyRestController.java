package edu.endava.tempr.api.controller;

import edu.endava.tempr.api.assembler.UserAssembler;
import edu.endava.tempr.api.service.SecurityUserDetailsService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.common.UserDto;
import edu.endava.tempr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MyRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAssembler userAssembler;

    @RequestMapping(value = "/user/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtoList = users.stream().map(user -> userAssembler.toDto(user)).collect(Collectors.toList());
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/login/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> loginUser(@RequestBody UserDto userDto) {
        User user = userAssembler.toEntity(userDto);
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/register/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User user = userAssembler.toEntity(userDto);
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED); //201
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        SecurityUserDetailsService userDetailsService;

        /*@Autowired
        public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
            builder.userDetailsService(userDetailsService);
        }*/

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
            /*auth.inMemoryAuthentication()
                    .withUser("user1").password("secret1").roles("USER")
                    .and()
                    .withUser("user2").password("secret2").roles("USER");*/
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            //http.authorizeRequests().anyRequest().fullyAuthenticated();
            //http.authorizeRequests().anyRequest().authenticated();
            http.httpBasic();
            http.authorizeRequests()
                    .antMatchers("/user/register/").permitAll()
                    .anyRequest().authenticated();
            http.csrf().disable();
            // @formatter:on
        }
    }

}