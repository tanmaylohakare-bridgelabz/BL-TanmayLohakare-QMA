package com.bridgelabz.quantitymeasurement.app;

import com.bridgelabz.quantitymeasurement.config.DatabaseConfig;
import com.bridgelabz.quantitymeasurement.controller.QuantityController;
import com.bridgelabz.quantitymeasurement.dto.QuantityRequestDTO;
import com.bridgelabz.quantitymeasurement.dto.ResponseDTO;
import com.bridgelabz.quantitymeasurement.repository.IQuantityRepository;
import com.bridgelabz.quantitymeasurement.repository.QuantityRepositoryImpl;
import com.bridgelabz.quantitymeasurement.service.IQuantityService;
import com.bridgelabz.quantitymeasurement.service.QuantityService;

// UC15: N-Tier Architecture Refactoring
// UC16: Database Integration with JDBC (HikariCP Pool)
public class QuantityApplication {

    public static void main(String[] args) {
        System.out.println("--- UC15 & UC16: N-Tier Architecture & Database Initialization ---");
        
        // Setup Repository (Database Connection & Schema)
        IQuantityRepository quantityRepository = new QuantityRepositoryImpl();
        
        // 1. Dependency Injection Pattern (Manual wiring)
        IQuantityService quantityService = new QuantityService(quantityRepository);
        QuantityController quantityController = new QuantityController(quantityService);
        
        System.out.println("N-Tier Components & Database Initialized Successfully.");
        System.out.println("\n--- Testing N-Tier Controller (Operations will be persisted to DB) ---");

        // Test 1: Compare 1 Foot and 12 Inches
        QuantityRequestDTO compareRequest = new QuantityRequestDTO();
        compareRequest.setCategory("LENGTH");
        compareRequest.setValue1(1.0);
        compareRequest.setUnit1("FEET");
        compareRequest.setValue2(12.0);
        compareRequest.setUnit2("INCHES");
        
        ResponseDTO compareResponse = quantityController.compareQuantities(compareRequest);
        System.out.println("Compare 1 Foot & 12 Inches: " + compareResponse.getMessage() + " | Result: " + compareResponse.getData());

        // Test 2: Add 2 Inches and 5 Centimeters to get Centimeters
        QuantityRequestDTO addRequest = new QuantityRequestDTO();
        addRequest.setCategory("LENGTH");
        addRequest.setValue1(2.0);
        addRequest.setUnit1("INCHES");
        addRequest.setValue2(5.0);
        addRequest.setUnit2("CENTIMETER");
        addRequest.setTargetUnit("CENTIMETER");

        ResponseDTO addResponse = quantityController.addQuantities(addRequest);
        System.out.println("Add 2 Inches & 5 Centimeters (target CM): " + addResponse.getMessage() + " | Result: " + addResponse.getData() + " CM");

        // Test 3: Convert 1 Gallon to Litres
        QuantityRequestDTO convertRequest = new QuantityRequestDTO();
        convertRequest.setCategory("VOLUME");
        convertRequest.setValue1(1.0);
        convertRequest.setUnit1("GALLON");
        convertRequest.setTargetUnit("LITRE");

        ResponseDTO convertResponse = quantityController.convertQuantity(convertRequest);
        System.out.println("Convert 1 Gallon to Litres: " + convertResponse.getMessage() + " | Result: " + convertResponse.getData() + " Litres");

        System.out.println("\nUC16 Refactoring Complete. Records persisted to database via HikariCP.");
        
        // Clean up resources
        DatabaseConfig.closePool();
        System.out.println("Database connection pool closed successfully.");
    }
}
