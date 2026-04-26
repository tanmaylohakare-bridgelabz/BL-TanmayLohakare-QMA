package com.bridgelabz.quantitymeasurement.interfaces;

/**
 * Base interface for measurement units.
 */
public interface Unit {
    
    /**
     * @return conversion factor to the base unit for this category
     */
    double getBaseUnitConversionFactor();
}
