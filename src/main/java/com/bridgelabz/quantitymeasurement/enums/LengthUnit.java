package com.bridgelabz.quantitymeasurement.enums;

import com.bridgelabz.quantitymeasurement.interfaces.Unit;

public enum LengthUnit implements Unit {
    FEET(12.0),
    INCHES(1.0),
    YARD(36.0),
    CENTIMETER(0.4);

    private final double baseUnitConversionFactor;

    LengthUnit(double baseUnitConversionFactor) {
        this.baseUnitConversionFactor = baseUnitConversionFactor;
    }

    // UC8: Implemented isolated conversion math
    @Override
    public double convertToBaseUnit(double value) {
        return value * this.baseUnitConversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / this.baseUnitConversionFactor;
    }
}
