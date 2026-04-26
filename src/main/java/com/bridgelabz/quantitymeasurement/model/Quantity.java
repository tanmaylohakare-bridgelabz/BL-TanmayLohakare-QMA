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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Quantity<?> quantity = (Quantity<?>) obj;
        
        if (!this.unit.getClass().equals(quantity.unit.getClass())) {
            return false;
        }

        double thisBaseValue = this.value * this.unit.getBaseUnitConversionFactor();
        double otherBaseValue = quantity.value * quantity.unit.getBaseUnitConversionFactor();
        return Math.abs(thisBaseValue - otherBaseValue) < DIFFERENCE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value * unit.getBaseUnitConversionFactor());
    }
}
