package edu.endava.tempr;

import edu.endava.tempr.api.service.ThermostatLogService;
import edu.endava.tempr.api.service.ThermostatService;
import edu.endava.tempr.api.service.UserService;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.ThermostatLog;
import edu.endava.tempr.model.User;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner addDefaultUser(UserService userService, ThermostatService thermostatService, ThermostatLogService thermostatLogService) {
        return (args) -> {
            User defUser = userService.createUser(new User("user", "user", "John", "Doe", "user@tempr.com"));
            Thermostat defThermostat = new Thermostat();
            defThermostat.setName("Device-1");
            defThermostat = thermostatService.createThermostat(defUser, defThermostat);

            defThermostat = new Thermostat();
            defThermostat.setName("Device-2");
            defThermostat = thermostatService.createThermostat(defUser, defThermostat);

            User user = userService.findByName("user");
            List<Thermostat> k = user.getThermostatList();
            for (Thermostat t : k) {
                System.out.println(t.toString());
            }

            Random rand = new Random();

            // Adding random logs
            //DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
            //DateTime newDate = new DateTime();
            for(int i=0;i<10;i++){
                ThermostatLog thermostatLog1 = new ThermostatLog();
                thermostatLog1.setToken(defThermostat.getToken());
                thermostatLog1.setLogTimeStamp(LocalDateTime.now());
                int randTemperature = rand.nextInt(7) + 20;
                thermostatLog1.setIntTemp(Integer.toString(randTemperature));
                thermostatLogService.create(thermostatLog1);
                //newDate = newDate.minusDays(1);
            }

            ThermostatLog fetchLog = thermostatLogService.getLatest(defThermostat.getToken());
            LOG.info("Got the latest log {}",fetchLog.toString());

            //LOG.info("Check out all after ");
            //List<ThermostatLog> fetchLogs = thermostatLogService.getLastTenDays(defThermostat.getToken());
            //for(ThermostatLog t: fetchLogs){
            //    LOG.info(t.toString());
           // }

        };
    }
}
