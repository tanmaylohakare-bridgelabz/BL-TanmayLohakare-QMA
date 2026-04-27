package com.bridgelabz.quantitymeasurement.enums;

import com.bridgelabz.quantitymeasurement.interfaces.Unit;

// UC11: Volume Measurement Support Enum for Scalability
public enum VolumeUnit implements Unit {
    GALLON(3.78),
    LITRE(1.0),
    MILLILITER(0.001);

    private final double baseUnitConversionFactor;

    VolumeUnit(double baseUnitConversionFactor) {
        this.baseUnitConversionFactor = baseUnitConversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * this.baseUnitConversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / this.baseUnitConversionFactor;
    }
}
