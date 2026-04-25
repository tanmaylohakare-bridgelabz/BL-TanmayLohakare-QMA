package com.bridgelabz.quantitymeasurement.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeetTest {

    @Test
    public void givenZeroFeet_WhenComparedWithZeroFeet_ShouldReturnEqual() {
        Feet feet1 = new Feet(0.0);
        Feet feet2 = new Feet(0.0);

        Assertions.assertEquals(feet1, feet2);
    }

    @Test
    public void givenOneFeet_WhenComparedWithSameReference_ShouldReturnEqual() {
        Feet feet = new Feet(1.0);

        Assertions.assertEquals(feet, feet);
    }

    @Test
    public void givenFeet_WhenComparedWithNull_ShouldReturnFalse() {
        Feet feet = new Feet(1.0);

        Assertions.assertNotEquals(feet, null);
    }

    @Test
    public void givenFeet_WhenComparedWithDifferentType_ShouldReturnFalse() {
        Feet feet = new Feet(1.0);

        Assertions.assertNotEquals(feet, "1.0");
    }

    @Test
    public void givenOneFeet_WhenComparedWithOneFeet_ShouldReturnEqual() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        Assertions.assertEquals(feet1, feet2);
    }

    @Test
    public void givenOneFeet_WhenComparedWithTwoFeet_ShouldReturnNotEqual() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(2.0);

        Assertions.assertNotEquals(feet1, feet2);
    }
}