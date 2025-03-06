package net.rankedproject.privateapi;

import net.rankedproject.privateapi.controller.RankedPlayer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PrivateApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivateApiApplication.class, args);
    }
}
