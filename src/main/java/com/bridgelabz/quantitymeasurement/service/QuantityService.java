package com.bridgelabz.quantitymeasurement.service;

import com.bridgelabz.quantitymeasurement.dto.QuantityRequestDTO;
import com.bridgelabz.quantitymeasurement.enums.LengthUnit;
import com.bridgelabz.quantitymeasurement.enums.TemperatureUnit;
import com.bridgelabz.quantitymeasurement.enums.VolumeUnit;
import com.bridgelabz.quantitymeasurement.enums.WeightUnit;
import com.bridgelabz.quantitymeasurement.interfaces.Unit;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.model.QuantityRecord;
import com.bridgelabz.quantitymeasurement.repository.IQuantityRepository;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// UC15: Service Layer enforcing Single Responsibility Principle (SRP)
// UC16: Layer Integration with Database Persistence
// UC17: Spring Services, Dependency Injection, JPA Integration, Logging, Scopes
@Service
@Scope("singleton")
public class QuantityService implements IQuantityService {

    private static final Logger logger = LoggerFactory.getLogger(QuantityService.class);
    private final IQuantityRepository repository;

    // Dependency Injection (Spring Autowired)
    @Autowired
    public QuantityService(IQuantityRepository repository) {
        this.repository = repository;
        logger.info("QuantityService Initialized (Singleton Scope)");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Unit getUnitEnum(String category, String unitStr) {
        if (category == null || unitStr == null) {
            logger.error("Category or Unit is null");
            throw new IllegalArgumentException("Category and Unit must not be null.");
        }
        try {
            switch (category.toUpperCase()) {
                case "LENGTH": return LengthUnit.valueOf(unitStr.toUpperCase());
                case "WEIGHT": return WeightUnit.valueOf(unitStr.toUpperCase());
                case "VOLUME": return VolumeUnit.valueOf(unitStr.toUpperCase());
                case "TEMPERATURE": return TemperatureUnit.valueOf(unitStr.toUpperCase());
                default: 
                    logger.error("Unknown Category: {}", category);
                    throw new IllegalArgumentException("Unknown Category: " + category);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Invalid unit '{}' for category '{}'", unitStr, category);
            throw new IllegalArgumentException("Invalid unit '" + unitStr + "' for category '" + category + "'.");
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double convertQuantity(QuantityRequestDTO requestDTO) {
        logger.info("Processing conversion request for {} to {}", requestDTO.getUnit1(), requestDTO.getTargetUnit());
        Unit sourceUnit = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit targetUnitEnum = getUnitEnum(requestDTO.getCategory(), requestDTO.getTargetUnit());
        
        Quantity quantity = new Quantity<>(requestDTO.getValue1(), sourceUnit);
        Quantity converted = quantity.convertTo(targetUnitEnum);
        double result = converted.getValue();
        
        // UC17: Persist with Spring Data JPA
        if (repository != null) {
            repository.save(new QuantityRecord(
                "CONVERT", requestDTO.getValue1(), requestDTO.getUnit1(), null, null, requestDTO.getTargetUnit(), result
            ));
            logger.info("Saved CONVERT operation to database");
        }
        
        return result;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double compareQuantities(QuantityRequestDTO requestDTO) {
        logger.info("Processing comparison request for {} and {}", requestDTO.getUnit1(), requestDTO.getUnit2());
        Unit unit1 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit unit2 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit2());
        
        Quantity q1 = new Quantity<>(requestDTO.getValue1(), unit1);
        Quantity q2 = new Quantity<>(requestDTO.getValue2(), unit2);
        
        double result = q1.equals(q2) ? 1.0 : 0.0;
        
        // UC17: Persist with Spring Data JPA
        if (repository != null) {
            repository.save(new QuantityRecord(
                "COMPARE", requestDTO.getValue1(), requestDTO.getUnit1(), requestDTO.getValue2(), requestDTO.getUnit2(), null, result
            ));
            logger.info("Saved COMPARE operation to database");
        }
        
        return result;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double addQuantities(QuantityRequestDTO requestDTO) {
        logger.info("Processing addition request for {} and {} to target {}", requestDTO.getUnit1(), requestDTO.getUnit2(), requestDTO.getTargetUnit());
        Unit unit1 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit unit2 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit2());
        Unit targetUnitEnum = getUnitEnum(requestDTO.getCategory(), requestDTO.getTargetUnit());
        
        Quantity q1 = new Quantity<>(requestDTO.getValue1(), unit1);
        Quantity q2 = new Quantity<>(requestDTO.getValue2(), unit2);
        
        Quantity res = q1.add(q2, targetUnitEnum);
        double result = res.getValue();
        
        // UC17: Persist with Spring Data JPA
        if (repository != null) {
            repository.save(new QuantityRecord(
                "ADD", requestDTO.getValue1(), requestDTO.getUnit1(), requestDTO.getValue2(), requestDTO.getUnit2(), requestDTO.getTargetUnit(), result
            ));
            logger.info("Saved ADD operation to database");
        }
        
        return result;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double subtractQuantities(QuantityRequestDTO requestDTO) {
        logger.info("Processing subtraction request for {} and {} to target {}", requestDTO.getUnit1(), requestDTO.getUnit2(), requestDTO.getTargetUnit());
        Unit unit1 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit unit2 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit2());
        Unit targetUnitEnum = getUnitEnum(requestDTO.getCategory(), requestDTO.getTargetUnit());
        
        Quantity q1 = new Quantity<>(requestDTO.getValue1(), unit1);
        Quantity q2 = new Quantity<>(requestDTO.getValue2(), unit2);
        
        Quantity res = q1.subtract(q2, targetUnitEnum);
        double result = res.getValue();
        
        // UC17: Persist with Spring Data JPA
        if (repository != null) {
            repository.save(new QuantityRecord(
                "SUBTRACT", requestDTO.getValue1(), requestDTO.getUnit1(), requestDTO.getValue2(), requestDTO.getUnit2(), requestDTO.getTargetUnit(), result
            ));
            logger.info("Saved SUBTRACT operation to database");
        }
        
        return result;
    }
}
