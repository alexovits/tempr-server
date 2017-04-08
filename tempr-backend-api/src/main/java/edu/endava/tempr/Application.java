package edu.endava.tempr;

import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
import edu.endava.tempr.model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    private static final int daysToGenerate = 10;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner addDefaultUser(UserService userService, ThermostatService thermostatService) {
        return (args) -> {
            // Create a demo ADMIN
            User adminUser = userService.createUser(new User("admin", "admin", "Admin", "Janos", "admin@tempr.com", UserType.ADMIN));
            // Create a demo user
            User defUser = userService.createUser(new User("user", "user", "John", "Doe", "user@tempr.com", UserType.USER));

            // Create demo devices for the user
            Thermostat defThermostat = new Thermostat();
            defThermostat.setName("Device-1");
            thermostatService.createThermostat(defUser, defThermostat);
            defThermostat = new Thermostat();
            defThermostat.setName("Device-2");
            defThermostat.setDesiredTemperature(19);
            defThermostat = thermostatService.createThermostat(defUser, defThermostat);

            // Showing every thermostat of a user
            User user = userService.findByName("user");
            List<Thermostat> k = user.getThermostatList();
            for (Thermostat t : k) {
                LOG.info(t.toString());
            }

            // Adding random logs for the last ten days to the "Device-2" thermostat of user "user"
            Random rand = new Random();
            LocalDateTime newDate = LocalDateTime.now();
            /*for(int i=0;i<daysToGenerate;i++){
                ThermostatLog randomThermostatLog = new ThermostatLog();
                randomThermostatLog.setToken(defThermostat.getToken());
                randomThermostatLog.setLogTimeStamp(newDate);
                int randTemperature = rand.nextInt(7) + 20;
                randomThermostatLog.setIntTemp(Integer.toString(randTemperature));
                thermostatLogService.create(randomThermostatLog);
                newDate = newDate.minusDays(1);
            }

            // Get the latest log of the
            ThermostatLog lastLog = thermostatLogService.getLatest(defThermostat.getToken());
            LOG.info("Got the latest log {}",lastLog.toString());

            LOG.info("Get the logs from the last {} days", daysToGenerate);
            List<ThermostatLog> fetchedLogs = thermostatLogService.getLastTenDays(defThermostat.getToken());
            for(ThermostatLog fetchedLog: fetchedLogs){
                LOG.info(fetchedLog.toString());
            }*/

        };
    }
}
