package com.bridgelabz.quantitymeasurement.interfaces;

public interface Unit {
    // UC8: Delegated conversion math to Unit interface
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);

    // UC14: Selective Arithmetic Support using Default Methods (Capability-Based Design)
    default boolean supportsArithmetic() {
        return true;
    }
}
