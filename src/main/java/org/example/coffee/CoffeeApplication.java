package org.example.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "org.example.coffee.config")
public class CoffeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeApplication.class, args);
    }

}
