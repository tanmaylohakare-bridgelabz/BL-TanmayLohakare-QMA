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

    // UC8: Delegated base value conversion math to Unit interface
    public Quantity<T> convertTo(T targetUnit) {
        double newBaseValue = this.getBaseValue();
        double targetValue = targetUnit.convertFromBaseUnit(newBaseValue);
        return new Quantity<>(targetValue, targetUnit);
    }

    // UC8: Delegated base value calculation to Unit interface
    private double getBaseValue() {
        return this.unit.convertToBaseUnit(this.value);
    }

    // UC6 & UC7: Addition of Two Length Units (Overloaded)
    public Quantity<T> add(Quantity<T> other) {
        return this.add(other, this.unit);
    }

    public Quantity<T> add(Quantity<T> other, T targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Cannot add a null quantity or specify a null target unit.");
        }
        
        // UC10: Runtime Cross-Category Type Safety logic for Enums
        Class<?> thisCategory = this.unit instanceof Enum ? ((Enum<?>) this.unit).getDeclaringClass() : this.unit.getClass();
        Class<?> otherCategory = other.unit instanceof Enum ? ((Enum<?>) other.unit).getDeclaringClass() : other.unit.getClass();
        
        if (!thisCategory.equals(otherCategory)) {
            throw new IllegalArgumentException("Cannot add quantities of different measurement categories.");
        }

        double totalBaseValue = this.getBaseValue() + other.getBaseValue();
        double newValue = targetUnit.convertFromBaseUnit(totalBaseValue);
        
        return new Quantity<>(newValue, targetUnit);
    }

    public boolean equals(Quantity<T> quantity) {
        if (quantity == null) {
            return false;
        }

        // UC10: Runtime Cross-Category Type Safety logic for Enums
        Class<?> thisCategory = this.unit instanceof Enum ? ((Enum<?>) this.unit).getDeclaringClass() : this.unit.getClass();
        Class<?> otherCategory = quantity.unit instanceof Enum ? ((Enum<?>) quantity.unit).getDeclaringClass() : quantity.unit.getClass();
        
        if (!thisCategory.equals(otherCategory)) {
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
