package com.bridgelabz.quantitymeasurement.service;

import com.bridgelabz.quantitymeasurement.dto.QuantityRequestDTO;
import com.bridgelabz.quantitymeasurement.enums.LengthUnit;
import com.bridgelabz.quantitymeasurement.enums.TemperatureUnit;
import com.bridgelabz.quantitymeasurement.enums.VolumeUnit;
import com.bridgelabz.quantitymeasurement.enums.WeightUnit;
import com.bridgelabz.quantitymeasurement.interfaces.Unit;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import org.springframework.stereotype.Service;

// UC15: Service Layer enforcing Single Responsibility Principle (SRP)
@Service
public class QuantityService implements IQuantityService {

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
        return converted.getValue();
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double compareQuantities(QuantityRequestDTO requestDTO) {
        Unit unit1 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit unit2 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit2());
        
        Quantity q1 = new Quantity<>(requestDTO.getValue1(), unit1);
        Quantity q2 = new Quantity<>(requestDTO.getValue2(), unit2);
        
        return q1.equals(q2) ? 1.0 : 0.0;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double addQuantities(QuantityRequestDTO requestDTO) {
        Unit unit1 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit unit2 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit2());
        Unit targetUnitEnum = getUnitEnum(requestDTO.getCategory(), requestDTO.getTargetUnit());
        
        Quantity q1 = new Quantity<>(requestDTO.getValue1(), unit1);
        Quantity q2 = new Quantity<>(requestDTO.getValue2(), unit2);
        
        Quantity result = q1.add(q2, targetUnitEnum);
        return result.getValue();
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public double subtractQuantities(QuantityRequestDTO requestDTO) {
        Unit unit1 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit1());
        Unit unit2 = getUnitEnum(requestDTO.getCategory(), requestDTO.getUnit2());
        Unit targetUnitEnum = getUnitEnum(requestDTO.getCategory(), requestDTO.getTargetUnit());
        
        Quantity q1 = new Quantity<>(requestDTO.getValue1(), unit1);
        Quantity q2 = new Quantity<>(requestDTO.getValue2(), unit2);
        
        Quantity result = q1.subtract(q2, targetUnitEnum);
        return result.getValue();
    }
}
