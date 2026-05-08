package com.bridgelabz.qmaservice.enums;

import com.bridgelabz.qmaservice.interfaces.Unit;

public enum VolumeUnit implements Unit {
    GALLON(3.78),
    LITRE(1.0),
    MILLILITRE(0.001);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
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
