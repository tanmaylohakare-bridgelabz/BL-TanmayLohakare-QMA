package com.bridgelabz.qmaservice.enums;

import com.bridgelabz.qmaservice.interfaces.Unit;

public enum TemperatureUnit implements Unit {
    CELSIUS(1.0, 0.0),
    FAHRENHEIT(1.8, 32.0);

    private final double factor;
    private final double offset;

    TemperatureUnit(double factor, double offset) {
        this.factor = factor;
        this.offset = offset;
    }

    @Override
    public double convertToBaseUnit(double value) {
        if (this == CELSIUS) return value;
        return (value - offset) / factor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        if (this == CELSIUS) return baseValue;
        return (baseValue * factor) + offset;
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }
}
