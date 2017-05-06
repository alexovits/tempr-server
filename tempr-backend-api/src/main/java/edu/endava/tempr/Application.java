package edu.endava.tempr;

import edu.endava.tempr.api.service.*;
import edu.endava.tempr.common.HeatingCircuitDto;
import edu.endava.tempr.common.SensorDto;
import edu.endava.tempr.model.HeatingCircuit;
import edu.endava.tempr.model.Thermostat;
import edu.endava.tempr.model.User;
import edu.endava.tempr.model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

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

            // Create demo thermostat for the user
            Thermostat defThermostat = new Thermostat();
            defThermostat.setName("Thermo-1");
            defThermostat = thermostatService.createThermostat(defUser, defThermostat);

            // Showing every thermostat of a user
            User user = userService.findByName("user");
            //Thermostat k = user.getThermostat();
            LOG.info("User {} has thermostat {}",user.getUsername(),defThermostat);

            // Add heating circuits to the thermostat & sensor
            HeatingCircuit heatingCircuit;
            HeatingCircuitDto heatingCircuitDto = new HeatingCircuitDto();
            heatingCircuitDto.setName("Nagyszoba");
            heatingCircuitDto.setSensorChipId((long) 8670624);
            heatingCircuitDto.setThermostatToken(defThermostat.getToken());
            heatingCircuit = heatingCircuitService.create(heatingCircuitDto);

            // Add another hc
            heatingCircuit = new HeatingCircuit();
            heatingCircuitDto = new HeatingCircuitDto();
            heatingCircuitDto.setName("Nappali");
            heatingCircuitDto.setSensorChipId((long) 2453634);
            heatingCircuitDto.setThermostatToken(defThermostat.getToken());
            heatingCircuit = heatingCircuitService.create(heatingCircuitDto);

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
