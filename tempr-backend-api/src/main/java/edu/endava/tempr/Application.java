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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            User defUser = userService.createUser(new User("user","user","John","Doe","user@tempr.com"));
            Thermostat defThermostat = new Thermostat();
            defThermostat.setName("Device-1");
            defThermostat = thermostatService.createThermostat(defUser,defThermostat);

            defThermostat = new Thermostat();
            defThermostat.setName("Device-2");
            defThermostat = thermostatService.createThermostat(defUser,defThermostat);

            User user = userService.findByName("user");
            List<Thermostat> k = user.getThermostatList();
            for(Thermostat t : k){
                System.out.println(t.toString());
            }

            // Adding log 1
            try {
                String startDateString = "2017/01/10 13:42:08";
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date newDate;
                newDate = df.parse(startDateString);
                ThermostatLog thermostatLog1 = new ThermostatLog();
                thermostatLog1.setToken(defThermostat.getToken());
                thermostatLog1.setLogTimeStamp(newDate);
                thermostatLog1.setIntTemp("23");
                thermostatLogService.create(thermostatLog1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Adding log 2
            try {
                String startDateString = "2017/01/11 18:42:08";
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date newDate;
                newDate = df.parse(startDateString);
                ThermostatLog thermostatLog1 = new ThermostatLog();
                thermostatLog1.setToken(defThermostat.getToken());
                thermostatLog1.setLogTimeStamp(newDate);
                thermostatLog1.setIntTemp("22");
                thermostatLogService.create(thermostatLog1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Adding log 3
            try {
                String startDateString = "2017/01/03 13:42:08";
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date newDate;
                newDate = df.parse(startDateString);
                ThermostatLog thermostatLog1 = new ThermostatLog();
                thermostatLog1.setToken(defThermostat.getToken());
                thermostatLog1.setLogTimeStamp(newDate);
                thermostatLog1.setIntTemp("23");
                thermostatLogService.create(thermostatLog1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            ThermostatLog fetchLog = thermostatLogService.getLatest();
            LOG.info("Got the latest log {}",fetchLog.toString());

            LOG.info("Check out all after ");
            List<ThermostatLog> fetchLogs = thermostatLogService.getLastTenDays();
            for(ThermostatLog t: fetchLogs){
                LOG.info(t.toString());
            }
        };
    }
}
