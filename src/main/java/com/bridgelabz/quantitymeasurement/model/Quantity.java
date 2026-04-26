package com.bridgelabz.quantitymeasurement.model;

import com.bridgelabz.quantitymeasurement.interfaces.Unit;
import java.util.Objects;

public class Quantity<T extends Unit> {

    private final double value;
    private final T unit;
    private static final double DIFFERENCE = 0.0001;

    public Quantity(double value, T unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public T getUnit() {
        return unit;
    }

    public Quantity<T> convertTo(T targetUnit) {
        double newBaseValue = this.getBaseValue();
        double targetValue = newBaseValue / targetUnit.getBaseUnitConversionFactor();
        return new Quantity<>(targetValue, targetUnit);
    }

    private double getBaseValue() {
        return this.value * this.unit.getBaseUnitConversionFactor();
    }

    // UC6: Addition of Two Length Units
    public Quantity<T> add(Quantity<T> other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot add a null quantity.");
        }
        
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot add quantities of different measurement categories.");
        }

        double totalBaseValue = this.getBaseValue() + other.getBaseValue();
        double newValue = totalBaseValue / this.unit.getBaseUnitConversionFactor();
        
        return new Quantity<>(newValue, this.unit);
    }

    public boolean equals(Quantity<T> quantity) {
        if (quantity == null) {
            return false;
        }

        if (!this.unit.getClass().equals(quantity.unit.getClass())) {
            return false;
        }

        return Math.abs(this.getBaseValue() - quantity.getBaseValue()) < DIFFERENCE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Quantity<T> quantity = (Quantity<T>) obj;
        return this.equals(quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getBaseValue());
    }
}
