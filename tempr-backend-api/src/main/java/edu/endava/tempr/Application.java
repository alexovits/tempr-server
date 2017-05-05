package edu.endava.tempr;

import edu.endava.tempr.api.service.*;
import edu.endava.tempr.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.util.List;

@EntityScan(
        basePackageClasses = {Application.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    private static final int daysToGenerate = 10;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner addDefaultUser(SensorLogService sensorLogService, UserService userService, ThermostatService thermostatService, HeatingCircuitService heatingCircuitService, SensorService sensorService) {
        return (args) -> {
            // Create a demo ADMIN
            User adminUser = userService.createUser(new User("admin", "admin", "Admin", "Janos", "admin@tempr.com", UserType.ADMIN));
            // Create a demo user
            User defUser = userService.createUser(new User("user", "user", "John", "Doe", "user@tempr.com", UserType.USER));

            // Create demo devices for the user
            Thermostat defThermostat = new Thermostat();
            defThermostat.setName("Thermo-1");
            defThermostat.setDesiredTemperature(19);
            defThermostat = thermostatService.createThermostat(defUser, defThermostat);

            // Showing every thermostat of a user
            User user = userService.findByName("user");
            List<Thermostat> k = user.getThermostatList();
            for (Thermostat t : k) {
                LOG.info(t.toString());
            }

            // Add heating circuits to the thermostat & sensor
            HeatingCircuit heatingCircuit = new HeatingCircuit();
            heatingCircuit.setName("Nagyszoba");
            heatingCircuit = heatingCircuitService.create(heatingCircuit, defThermostat.getToken());
            Sensor sensor = new Sensor();
            sensor.setChipId((long) 8670624);
            sensorService.create(sensor, heatingCircuit.getId());

            // Add another hc
            heatingCircuit = new HeatingCircuit();
            heatingCircuit.setName("Nappali");
            heatingCircuit = heatingCircuitService.create(heatingCircuit, defThermostat.getToken());
            sensor = new Sensor();
            sensor.setChipId((long) 460059);
            sensorService.create(sensor, heatingCircuit.getId());

            LOG.info(heatingCircuitService.findByChipId((long) 8670624).getName());

            HeatingCircuit hc = heatingCircuitService.findByChipId((long) 8670624);

            sensorLogService.create(10,hc);

            LOG.info("Here is {}", thermostatService.getTemperatures(defThermostat.getToken()));

            // Adding random logs for the last ten days to the "Device-2" thermostat of user "user"
             /*Random rand = new Random();
            LocalDateTime newDate = LocalDateTime.now();
            for(int i=0;i<daysToGenerate;i++){
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
