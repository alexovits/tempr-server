package edu.endava.tempr;

import edu.endava.tempr.api.service.*;
import edu.endava.tempr.common.HeatingCircuitDto;
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

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

@EntityScan(
        basePackageClasses = {Application.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    private static final int DAYS_TO_GENERATE = 25;
    private static final int HOURS_TO_GENERATE = 24;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner addDefaultUser(SuggestionService suggestionService, SensorLogService sensorLogService, UserService userService, ThermostatService thermostatService, HeatingCircuitService heatingCircuitService, SensorService sensorService) {
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
            HeatingCircuitDto heatingCircuitDto = new HeatingCircuitDto();
            heatingCircuitDto.setName("Nagyszoba");
            heatingCircuitDto.setSensorChipId((long) 8670624);
            heatingCircuitDto.setThermostatToken(defThermostat.getToken());
            heatingCircuitService.create(heatingCircuitDto);

            // Add another hc
            heatingCircuitDto = new HeatingCircuitDto();
            heatingCircuitDto.setName("Nappali");
            heatingCircuitDto.setSensorChipId((long) 2453634);
            heatingCircuitDto.setThermostatToken(defThermostat.getToken());
            heatingCircuitService.create(heatingCircuitDto);

            LOG.info(heatingCircuitService.findByChipId((long) 8670624).getName());

            HeatingCircuit hc = heatingCircuitService.findByChipId((long) 8670624);

            // Adding random logs for the last ten days to the "Device-2" thermostat of user "user"
            Random rand = new Random();
            IntStream.range(0, DAYS_TO_GENERATE).forEachOrdered(day -> {
                IntStream.range(0, HOURS_TO_GENERATE).forEachOrdered(hour -> {
//                    int randTemperature = rand.nextInt(7) + 18;
                    int randTemperature = new Double(rand.nextGaussian()*3 + 21).intValue();// mean:21, stddev:3
                    sensorLogService.create(LocalDateTime.now().minusDays(day).minusHours(hour), randTemperature, hc);
                });
            });

            LOG.info("Here is {}", thermostatService.getTemperatures(defThermostat.getToken()));

            /*for(SensorLog s: sensorLogService.getLastWeeksLogs(hc.getId())){
                LOG.info("---> {}",s);
            }*/

            LOG.info("This is the shit:----> {}", suggestionService.getSuggestionTemperature(0,hc.getId()));
        };
    }
}
