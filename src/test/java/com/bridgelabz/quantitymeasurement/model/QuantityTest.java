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

    @Test
    public void givenThreeFeetAndOneYard_ShouldReturnEqual() {
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
        Assertions.assertEquals(feet, yard);
    }

    @Test
    public void givenOneFeetAndOneYard_ShouldReturnNotEqual() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
        Assertions.assertNotEquals(feet, yard);
    }

    @Test
    public void givenOneInchAndOneYard_ShouldReturnNotEqual() {
        Quantity<LengthUnit> inch = new Quantity<>(1.0, LengthUnit.INCHES);
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
        Assertions.assertNotEquals(inch, yard);
    }

    @Test
    public void givenOneYardAndThirtySixInches_ShouldReturnEqual() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<LengthUnit> inches = new Quantity<>(36.0, LengthUnit.INCHES);
        Assertions.assertEquals(yard, inches);
    }

    @Test
    public void givenThirtySixInchesAndOneYard_ShouldReturnEqual() {
        Quantity<LengthUnit> inches = new Quantity<>(36.0, LengthUnit.INCHES);
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
        Assertions.assertEquals(inches, yard);
    }

    @Test
    public void givenOneYardAndThreeFeet_ShouldReturnEqual() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        Assertions.assertEquals(yard, feet);
    }

    @Test
    public void givenTwoInchesAndFiveCentimeters_ShouldReturnEqual() {
        Quantity<LengthUnit> inches = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> centimeters = new Quantity<>(5.0, LengthUnit.CENTIMETER);
        Assertions.assertEquals(inches, centimeters);
    }

    @Test
    public void givenOneYard_WhenConvertedToInches_ShouldReturnThirtySixInches() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<LengthUnit> convertedToInches = yard.convertTo(LengthUnit.INCHES);
        
        Assertions.assertEquals(36.0, convertedToInches.getValue(), 0.0001);
        Assertions.assertEquals(LengthUnit.INCHES, convertedToInches.getUnit());
    }

    @Test
    public void givenTwoInches_WhenConvertedToCentimeters_ShouldReturnFiveCentimeters() {
        Quantity<LengthUnit> inches = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> convertedToCm = inches.convertTo(LengthUnit.CENTIMETER);
        
        Assertions.assertEquals(5.0, convertedToCm.getValue(), 0.0001);
        Assertions.assertEquals(LengthUnit.CENTIMETER, convertedToCm.getUnit());
    }

    @Test
    public void givenQuantity_WhenConverted_ShouldReturnNewInstanceForImmutability() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> convertedToInches = feet.convertTo(LengthUnit.INCHES);
        
        // Assert they are mathematically equal but are different object instances
        Assertions.assertEquals(feet, convertedToInches);
        Assertions.assertNotSame(feet, convertedToInches);
    }
}
