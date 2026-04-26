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

    @Override
    public double getBaseUnitConversionFactor() {
        return baseUnitConversionFactor;
    }
}
