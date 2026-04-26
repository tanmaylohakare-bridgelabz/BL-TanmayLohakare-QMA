package com.bridgelabz.quantitymeasurement.model;

import java.util.Objects;

public class Inches {

    private final double value;
    private static final double DIFFERENCE = 0.0001;

    public Inches(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    double toInches() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof Inches) {
            Inches inches = (Inches) obj;
            return Math.abs(this.toInches() - inches.toInches()) < DIFFERENCE;
        }

        if (obj instanceof Feet) {
            Feet feet = (Feet) obj;
            return Math.abs(this.toInches() - feet.toInches()) < DIFFERENCE;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toInches());
    }
}