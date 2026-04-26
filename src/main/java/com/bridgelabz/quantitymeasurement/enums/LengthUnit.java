package com.bridgelabz.quantitymeasurement.enums;

import com.bridgelabz.quantitymeasurement.interfaces.Unit;

public enum LengthUnit implements Unit {
    FEET(12.0),
    INCHES(1.0);

    private final double baseUnitConversionFactor;

    LengthUnit(double baseUnitConversionFactor) {
        this.baseUnitConversionFactor = baseUnitConversionFactor;
    }

    @Override
    public double getBaseUnitConversionFactor() {
        return baseUnitConversionFactor;
    }
}
