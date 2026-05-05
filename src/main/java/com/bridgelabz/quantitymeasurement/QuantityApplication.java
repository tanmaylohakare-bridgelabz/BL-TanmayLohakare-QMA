package com.bridgelabz.quantitymeasurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// UC15: N-Tier Architecture Refactoring
// UC16: Database Integration with JDBC (HikariCP Pool)
// UC17: Spring Boot Application with Spring MVC, JPA, Autowiring
@SpringBootApplication
public class QuantityApplication {

    public static void main(String[] args) {
        System.out.println("--- UC17: Starting Quantity Measurement Spring Boot Application ---");
        SpringApplication.run(QuantityApplication.class, args);
        System.out.println("--- Spring Boot Application Started Successfully ---");
    }
}
