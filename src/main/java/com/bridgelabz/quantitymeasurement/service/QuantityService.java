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

// UC15: Service Layer enforcing Single Responsibility Principle (SRP)
// UC16: Layer Integration with Database Persistence
public class QuantityService implements IQuantityService {

    private final IQuantityRepository repository;

    // Dependency Injection
    public QuantityService(IQuantityRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Unit getUnitEnum(String category, String unitStr) {
        if (category == null || unitStr == null) throw new IllegalArgumentException("Category and Unit must not be null.");
        try {
            switch (category.toUpperCase()) {
                case "LENGTH": return LengthUnit.valueOf(unitStr.toUpperCase());
                case "WEIGHT": return WeightUnit.valueOf(unitStr.toUpperCase());
                case "VOLUME": return VolumeUnit.valueOf(unitStr.toUpperCase());
                case "TEMPERATURE": return TemperatureUnit.valueOf(unitStr.toUpperCase());
                default: throw new IllegalArgumentException("Unknown Category: " + category);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid unit '" + unitStr + "' for category '" + category + "'.");
        }
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double convertQuantity(QuantityRequestDTO requestDTO) {
        Unit sourceUnit = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit targetUnitEnum = getUnitEnum(requestDTO.getCategory(), requestDTO.getTargetUnit());
        
        Quantity quantity = new Quantity<>(requestDTO.getValue1(), sourceUnit);
        Quantity converted = quantity.convertTo(targetUnitEnum);
        double result = converted.getValue();
        
        // UC16: Persist
        if (repository != null) {
            repository.save(new QuantityRecord(
                "CONVERT", requestDTO.getValue1(), requestDTO.getUnit1(), null, null, requestDTO.getTargetUnit(), result
            ));
        }
        
        return result;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double compareQuantities(QuantityRequestDTO requestDTO) {
        Unit unit1 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit unit2 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit2());
        
        Quantity q1 = new Quantity<>(requestDTO.getValue1(), unit1);
        Quantity q2 = new Quantity<>(requestDTO.getValue2(), unit2);
        
        double result = q1.equals(q2) ? 1.0 : 0.0;
        
        // UC16: Persist
        if (repository != null) {
            repository.save(new QuantityRecord(
                "COMPARE", requestDTO.getValue1(), requestDTO.getUnit1(), requestDTO.getValue2(), requestDTO.getUnit2(), null, result
            ));
        }
        
        return result;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double addQuantities(QuantityRequestDTO requestDTO) {
        Unit unit1 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit unit2 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit2());
        Unit targetUnitEnum = getUnitEnum(requestDTO.getCategory(), requestDTO.getTargetUnit());
        
        Quantity q1 = new Quantity<>(requestDTO.getValue1(), unit1);
        Quantity q2 = new Quantity<>(requestDTO.getValue2(), unit2);
        
        Quantity res = q1.add(q2, targetUnitEnum);
        double result = res.getValue();
        
        // UC16: Persist
        if (repository != null) {
            repository.save(new QuantityRecord(
                "ADD", requestDTO.getValue1(), requestDTO.getUnit1(), requestDTO.getValue2(), requestDTO.getUnit2(), requestDTO.getTargetUnit(), result
            ));
        }
        
        return result;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double subtractQuantities(QuantityRequestDTO requestDTO) {
        Unit unit1 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit unit2 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit2());
        Unit targetUnitEnum = getUnitEnum(requestDTO.getCategory(), requestDTO.getTargetUnit());
        
        Quantity q1 = new Quantity<>(requestDTO.getValue1(), unit1);
        Quantity q2 = new Quantity<>(requestDTO.getValue2(), unit2);
        
        Quantity res = q1.subtract(q2, targetUnitEnum);
        double result = res.getValue();
        
        // UC16: Persist
        if (repository != null) {
            repository.save(new QuantityRecord(
                "SUBTRACT", requestDTO.getValue1(), requestDTO.getUnit1(), requestDTO.getValue2(), requestDTO.getUnit2(), requestDTO.getTargetUnit(), result
            ));
        }
        
        return result;
    }
}
