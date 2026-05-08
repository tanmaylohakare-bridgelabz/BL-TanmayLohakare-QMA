package com.bridgelabz.qmaservice.enums;

import com.bridgelabz.qmaservice.interfaces.Unit;

public enum LengthUnit implements Unit {
    FEET(12.0),
    INCH(1.0),
    YARD(36.0),
    CENTIMETER(0.4);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public boolean supportsArithmetic() {
        return true;
    }
}
