package com.bridgelabz.qmaservice.interfaces;

public interface Unit {
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    boolean supportsArithmetic();
}
