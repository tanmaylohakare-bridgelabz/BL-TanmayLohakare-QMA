package com.bridgelabz.quantitymeasurement.model;

import java.util.Objects;

public class Feet {

    private final double value;

    public Feet(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {

        // Check if object is null
        if (obj == null) {
            return false;
        }

        // Check same reference
        if (this == obj) {
            return true;
        }

        // Check object type
        if (getClass() != obj.getClass()) {
            return false;
        }

        Feet feet = (Feet) obj;

        // Compare floating point values
        return Double.compare(this.value, feet.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}