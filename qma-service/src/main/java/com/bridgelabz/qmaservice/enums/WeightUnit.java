package com.bridgelabz.qmaservice.enums;

import com.bridgelabz.qmaservice.interfaces.Unit;

public enum WeightUnit implements Unit {
    KILOGRAM(1.0),
    GRAM(0.001),
    TONNE(1000.0);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
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
