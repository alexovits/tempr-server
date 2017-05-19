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
    private static final long USER_SENSOR_ID_1 = 1269319;//Real
    private static final long USER_SENSOR_ID_2 = 2579345;
    private static final long ADMIN_SENSOR_ID_1 = 1188432;//Real
    private static final long ADMIN_SENSOR_ID_2 = 123;
    private static final long ADMIN_SENSOR_ID_3 = 456;
    private static final long ADMIN_SENSOR_ID_4 = 789;

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
            heatingCircuitDto.setName("Dorm Room");
            heatingCircuitDto.setSensorChipId(USER_SENSOR_ID_1);
            heatingCircuitDto.setThermostatToken(defThermostat.getToken());
            heatingCircuitService.create(heatingCircuitDto);

            // Add another hc
            heatingCircuitDto = new HeatingCircuitDto();
            heatingCircuitDto.setName("Downstairs");
            heatingCircuitDto.setSensorChipId(USER_SENSOR_ID_2);
            heatingCircuitDto.setThermostatToken(defThermostat.getToken());
            heatingCircuitService.create(heatingCircuitDto);

            LOG.info(heatingCircuitService.findByChipId(USER_SENSOR_ID_1).getName());

            HeatingCircuit hc = heatingCircuitService.findByChipId(USER_SENSOR_ID_1);

            // Adding random logs for the last ten days to the "Device-2" thermostat of user "user"
            Random rand = new Random();
            IntStream.range(0, DAYS_TO_GENERATE).forEachOrdered(day -> {
                IntStream.range(0, HOURS_TO_GENERATE).forEachOrdered(hour -> {
//                    int randTemperature = rand.nextInt(7) + 18;
                    int randTemperature = new Double(rand.nextGaussian()*3 + 21).intValue();// mean:21, stddev:3
                    sensorLogService.create(LocalDateTime.now().minusDays(day).minusHours(hour), randTemperature, hc);
                });
            });

            HeatingCircuit hc2 = heatingCircuitService.findByChipId(USER_SENSOR_ID_2);
            IntStream.range(0, DAYS_TO_GENERATE).forEachOrdered(day -> {
                IntStream.range(0, HOURS_TO_GENERATE).forEachOrdered(hour -> {
//                    int randTemperature = rand.nextInt(7) + 18;
                    int randTemperature = new Double(rand.nextGaussian()*3 + 21).intValue();// mean:21, stddev:3
                    sensorLogService.create(LocalDateTime.now().minusDays(day).minusHours(hour), randTemperature, hc2);
                });
            });

            //LOG.info("Here is {}", thermostatService.getTemperatures(defThermostat.getToken()));
            //LOG.info("All the suggestions: {}", suggestionService.getSuggestionTemperature(0,hc.getId()));

            // Mock for admin user ------------------------------------------------------------------------------

            // Create demo thermostat for the user
            defThermostat = new Thermostat();
            defThermostat.setName("Thermo-admin");
            defThermostat = thermostatService.createThermostat(adminUser, defThermostat);

            LOG.info("User {} has thermostat {}", adminUser.getUsername(), defThermostat);

            // Add heating circuits to the thermostat & sensor
            heatingCircuitDto = new HeatingCircuitDto();
            heatingCircuitDto.setName("Living Room");
            heatingCircuitDto.setSensorChipId(ADMIN_SENSOR_ID_1);
            heatingCircuitDto.setThermostatToken(defThermostat.getToken());
            heatingCircuitService.create(heatingCircuitDto);

            // Add another hc
            heatingCircuitDto = new HeatingCircuitDto();
            heatingCircuitDto.setName("Kitchen");
            heatingCircuitDto.setSensorChipId(ADMIN_SENSOR_ID_2);
            heatingCircuitDto.setThermostatToken(defThermostat.getToken());
            heatingCircuitService.create(heatingCircuitDto);

            // Add another hc
            heatingCircuitDto = new HeatingCircuitDto();
            heatingCircuitDto.setName("Bathroom");
            heatingCircuitDto.setSensorChipId(ADMIN_SENSOR_ID_3);
            heatingCircuitDto.setThermostatToken(defThermostat.getToken());
            heatingCircuitService.create(heatingCircuitDto);

            // Add another hc
            heatingCircuitDto = new HeatingCircuitDto();
            heatingCircuitDto.setName("Garage");
            heatingCircuitDto.setSensorChipId(ADMIN_SENSOR_ID_4);
            heatingCircuitDto.setThermostatToken(defThermostat.getToken());
            heatingCircuitService.create(heatingCircuitDto);

            LOG.info(heatingCircuitService.findByChipId(ADMIN_SENSOR_ID_1).getName());

            HeatingCircuit hcAdmin = heatingCircuitService.findByChipId(ADMIN_SENSOR_ID_1);

            IntStream.range(0, DAYS_TO_GENERATE).forEachOrdered(day -> {
                IntStream.range(0, HOURS_TO_GENERATE).forEachOrdered(hour -> {
                    int randTemperature = new Double(rand.nextGaussian()*3 + 21).intValue();// mean:21, stddev:3
                    sensorLogService.create(LocalDateTime.now().minusDays(day).minusHours(hour), randTemperature, hcAdmin);
                });
            });

            LOG.info("Here is {}", thermostatService.getTemperatures(defThermostat.getToken()));

            //LOG.info("This is the sample:----> {}", suggestionService.getSuggestionTemperature(1,hcAdmin.getId()));

            LocalDateTime currentDate = LocalDateTime.now();
            LOG.info("Working with day {} and segment {}", currentDate.getDayOfWeek().getValue(), currentDate.getHour());
            LOG.info("Before adding 23 log for now the suggestion is: {}", suggestionService.getSuggestionTemperature(currentDate.getDayOfWeek().getValue(), currentDate.getHour(), hcAdmin.getId()));
            sensorLogService.create(23,hcAdmin);
            LOG.info("After adding 23 log for now the suggestion is: {}", suggestionService.getSuggestionTemperature(currentDate.getDayOfWeek().getValue(), currentDate.getHour(), hcAdmin.getId()));

        };
    }
}
