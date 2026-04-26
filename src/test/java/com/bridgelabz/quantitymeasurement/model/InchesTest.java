package com.bridgelabz.quantitymeasurement.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bridgelabz.quantitymeasurement.model.Feet;
import com.bridgelabz.quantitymeasurement.model.Inches;

public class InchesTest {

    @Test
    public void givenOneFeetAndTwelveInches_ShouldReturnTrue() {
        Feet feet = new Feet(1.0);
        Inches inches = new Inches(12.0);

        assertTrue(feet.equals(inches));
    }

    @Test
    public void givenTwelveInchesAndOneFeet_ShouldReturnTrue() {
        Inches inches = new Inches(12.0);
        Feet feet = new Feet(1.0);

        assertTrue(inches.equals(feet));
    }

    @Test
    public void givenSameInches_ShouldReturnTrue() {
        Inches firstValue = new Inches(12.0);
        Inches secondValue = new Inches(12.0);

        assertTrue(firstValue.equals(secondValue));
    }

    @Test
    public void givenDifferentFeetAndInches_ShouldReturnFalse() {
        Feet feet = new Feet(1.0);
        Inches inches = new Inches(10.0);

        assertFalse(feet.equals(inches));
    }

    @Test
    public void givenInchesAndNull_ShouldReturnFalse() {
        Inches inches = new Inches(12.0);

        assertFalse(inches.equals(null));
    }
}