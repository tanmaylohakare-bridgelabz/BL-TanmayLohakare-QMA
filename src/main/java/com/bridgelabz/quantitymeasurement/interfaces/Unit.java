package com.bridgelabz.quantitymeasurement.interfaces;

public interface Unit {
    // UC8: Delegated conversion math to Unit interface
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
}
