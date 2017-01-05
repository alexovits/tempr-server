package edu.endava.tempr;

import edu.endava.tempr.api.service.UserService;
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
    public CommandLineRunner addDefaultUser(UserService userService) {
        return (args) -> {
            userService.createUser(new User("user","user","John","Doe","user@tempr.com"));
        };
    }
}
