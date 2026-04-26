package com.bridgelabz.quantitymeasurement.model;

import com.bridgelabz.quantitymeasurement.interfaces.Unit;
import java.util.Objects;

/**
 * Immutable value object representing a measurement quantity.
 * 
 * @param <T> unit category (e.g., LengthUnit)
 */
public class Quantity<T extends Unit> {

    private final double value;
    private final T unit;
    private static final double DIFFERENCE = 0.0001;

    /**
     * @param value numerical magnitude
     * @param unit  unit of measurement
     */
    public Quantity(double value, T unit) {
        this.value = value;
        this.unit = unit;
    }

    /**
     * @return numerical magnitude
     */
    public double getValue() {
        return value;
    }

    /**
     * @return unit of measurement
     */
    public T getUnit() {
        return unit;
    }

    /**
     * Converts the current quantity to the specified target unit.
     * 
     * @param targetUnit unit to convert to
     * @return new Quantity instance with converted value
     */
    public Quantity<T> convertTo(T targetUnit) {
        double newBaseValue = this.getBaseValue();
        double targetValue = newBaseValue / targetUnit.getBaseUnitConversionFactor();
        return new Quantity<>(targetValue, targetUnit);
    }

    /**
     * Returns the value normalized to the base unit.
     */
    private double getBaseValue() {
        return this.value * this.unit.getBaseUnitConversionFactor();
    }

    /**
     * Type-safe equals method.
     * 
     * @param quantity the other quantity
     * @return true if quantities map to the same base value
     */
    public boolean equals(Quantity<T> quantity) {
        if (quantity == null) {
            return false;
        }

        // restrict cross-category comparisons (e.g. can't compare length to volume later)
        if (!this.unit.getClass().equals(quantity.unit.getClass())) {
            return false;
        }

        // small delta to prevent floating point math weirdness
        return Math.abs(this.getBaseValue() - quantity.getBaseValue()) < DIFFERENCE;
    }

    /**
     * Compares this quantity to the specified object.
     * 
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // delegates to overloaded equals
        @SuppressWarnings("unchecked")
        Quantity<T> quantity = (Quantity<T>) obj;
        return this.equals(quantity);
    }

    /**
     * Returns a hash code value for the quantity.
     * 
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getBaseValue());
    }
}
