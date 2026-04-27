package com.bridgelabz.quantitymeasurement.enums;

import com.bridgelabz.quantitymeasurement.interfaces.Unit;

public enum WeightUnit implements Unit {
    GRAM(1.0),
    KILOGRAM(1000.0),
    TONNE(1000000.0);

    private final double baseUnitConversionFactor;

    WeightUnit(double baseUnitConversionFactor) {
        this.baseUnitConversionFactor = baseUnitConversionFactor;
    }

    // UC9: Implemented isolated conversion math for Weight
    @Override
    public double convertToBaseUnit(double value) {
        return value * this.baseUnitConversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / this.baseUnitConversionFactor;
    }
}
