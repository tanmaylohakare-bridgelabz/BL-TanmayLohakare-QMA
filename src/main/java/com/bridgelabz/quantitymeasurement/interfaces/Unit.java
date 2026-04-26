package com.bridgelabz.quantitymeasurement.interfaces;

public interface Unit {
    // forces all units to define how they convert back to a standard base (like inches)
    double getBaseUnitConversionFactor();
}
