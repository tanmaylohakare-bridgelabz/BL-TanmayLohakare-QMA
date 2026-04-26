package com.bridgelabz.quantitymeasurement.model;

import com.bridgelabz.quantitymeasurement.enums.LengthUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    public void givenZeroFeet_WhenComparedWithZeroFeet_ShouldReturnEqual() {
        Quantity<LengthUnit> feet1 = new Quantity<>(0.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(0.0, LengthUnit.FEET);
        Assertions.assertEquals(feet1, feet2);
    }

    @Test
    public void givenOneFeet_WhenComparedWithSameReference_ShouldReturnEqual() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Assertions.assertEquals(feet, feet);
    }

    @Test
    public void givenFeet_WhenComparedWithNull_ShouldReturnFalse() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Assertions.assertNotEquals(feet, null);
    }

    @Test
    public void givenFeet_WhenComparedWithDifferentType_ShouldReturnFalse() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Assertions.assertNotEquals(feet, "1.0");
    }

    @Test
    public void givenOneFeet_WhenComparedWithOneFeet_ShouldReturnEqual() {
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(1.0, LengthUnit.FEET);
        Assertions.assertEquals(feet1, feet2);
    }

    @Test
    public void givenOneFeet_WhenComparedWithTwoFeet_ShouldReturnNotEqual() {
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);
        Assertions.assertNotEquals(feet1, feet2);
    }

    @Test
    public void givenOneFeetAndTwelveInches_ShouldReturnEqual() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        Assertions.assertEquals(feet, inches);
    }

    @Test
    public void givenTwelveInchesAndOneFeet_ShouldReturnEqual() {
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Assertions.assertEquals(inches, feet);
    }

    @Test
    public void givenSameInches_ShouldReturnEqual() {
        Quantity<LengthUnit> firstValue = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> secondValue = new Quantity<>(12.0, LengthUnit.INCHES);
        Assertions.assertEquals(firstValue, secondValue);
    }

    @Test
    public void givenDifferentFeetAndInches_ShouldReturnNotEqual() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(10.0, LengthUnit.INCHES);
        Assertions.assertNotEquals(feet, inches);
    }

    @Test
    public void givenInchesAndNull_ShouldReturnNotEqual() {
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        Assertions.assertNotEquals(inches, null);
    }
}
