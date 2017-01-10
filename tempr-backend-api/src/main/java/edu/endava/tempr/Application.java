package edu.endava.tempr;

import edu.endava.tempr.api.service.ThermostatLogService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.ThermostatLog;
import edu.endava.tempr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner addDefaultUser(UserService userService, ThermostatService thermostatService, ThermostatLogService thermostatLogService) {
        return (args) -> {
            User defUser = userService.createUser(new User("user","user","John","Doe","user@tempr.com"));
            Thermostat defThermostat = new Thermostat();
            defThermostat.setUserId(defUser.getId());
            defThermostat.setName("Device-1");
            defThermostat = thermostatService.createThermostat(defUser,defThermostat);
            //defThermostat.setName("Device-2");
            //defThermostat = thermostatService.createThermostat(defUser,defThermostat);
        };
    }
}
