package com.bridgelabz.quantitymeasurement.model;

import com.bridgelabz.quantitymeasurement.enums.LengthUnit;
import com.bridgelabz.quantitymeasurement.enums.WeightUnit;
import com.bridgelabz.quantitymeasurement.enums.VolumeUnit;
import com.bridgelabz.quantitymeasurement.enums.TemperatureUnit;
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

    @Test
    public void givenTwoInchesAndTwoInches_WhenAdded_ShouldReturnFourInches() {
        Quantity<LengthUnit> inches1 = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> inches2 = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sum = inches1.add(inches2);
        Assertions.assertEquals(new Quantity<>(4.0, LengthUnit.INCHES), sum);
        Assertions.assertEquals(4.0, sum.getValue(), 0.0001);
        Assertions.assertEquals(LengthUnit.INCHES, sum.getUnit());
    }

    @Test
    public void givenOneFootAndTwoInches_WhenAdded_ShouldReturnFourteenInches() {
        Quantity<LengthUnit> foot = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sum = foot.add(inches);
        
        // Mathematical equality checks handle normalization automatically
        Assertions.assertEquals(new Quantity<>(14.0, LengthUnit.INCHES), sum);
    }

    @Test
    public void givenOneFootAndOneFoot_WhenAdded_ShouldReturnTwoFeet() {
        Quantity<LengthUnit> foot1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> foot2 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> sum = foot1.add(foot2);
        Assertions.assertEquals(new Quantity<>(2.0, LengthUnit.FEET), sum);
        Assertions.assertEquals(2.0, sum.getValue(), 0.0001);
        Assertions.assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    public void givenTwoInchesAndTwoAndHalfCentimeters_WhenAdded_ShouldReturnThreeInches() {
        Quantity<LengthUnit> inches = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> centimeters = new Quantity<>(2.5, LengthUnit.CENTIMETER);
        Quantity<LengthUnit> sum = inches.add(centimeters);
        Assertions.assertEquals(new Quantity<>(3.0, LengthUnit.INCHES), sum);
        Assertions.assertEquals(3.0, sum.getValue(), 0.0001);
        Assertions.assertEquals(LengthUnit.INCHES, sum.getUnit());
    }

    @Test
    public void givenQuantityAndNull_WhenAdded_ShouldThrowException() {
        Quantity<LengthUnit> inches = new Quantity<>(2.0, LengthUnit.INCHES);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inches.add(null);
        });
    }

    @Test
    public void givenTwoInchesAndTwoInches_WhenAddedWithTargetCentimeter_ShouldReturnTenCentimeters() {
        Quantity<LengthUnit> inches1 = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> inches2 = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sum = inches1.add(inches2, LengthUnit.CENTIMETER);
        Assertions.assertEquals(new Quantity<>(10.0, LengthUnit.CENTIMETER), sum);
        Assertions.assertEquals(10.0, sum.getValue(), 0.0001);
        Assertions.assertEquals(LengthUnit.CENTIMETER, sum.getUnit());
    }

    @Test
    public void givenOneFootAndTwoInches_WhenAddedWithTargetInches_ShouldReturnFourteenInches() {
        Quantity<LengthUnit> foot = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sum = foot.add(inches, LengthUnit.INCHES);
        Assertions.assertEquals(new Quantity<>(14.0, LengthUnit.INCHES), sum);
        Assertions.assertEquals(14.0, sum.getValue(), 0.0001);
        Assertions.assertEquals(LengthUnit.INCHES, sum.getUnit());
    }

    @Test
    public void givenQuantityAndNullTargetUnit_WhenAdded_ShouldThrowException() {
        Quantity<LengthUnit> inches = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> other = new Quantity<>(2.0, LengthUnit.INCHES);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inches.add(other, null);
        });
    }

    // UC9: Weight Measurement Tests
    @Test
    public void givenOneGramAndOneGram_WhenCompared_ShouldReturnEqual() {
        Quantity<WeightUnit> gram1 = new Quantity<>(1.0, WeightUnit.GRAM);
        Quantity<WeightUnit> gram2 = new Quantity<>(1.0, WeightUnit.GRAM);
        Assertions.assertTrue(gram1.equals(gram2));
    }

    @Test
    public void givenOneKilogramAndThousandGrams_WhenCompared_ShouldReturnEqual() {
        Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Assertions.assertTrue(kilogram.equals(grams));
    }

    @Test
    public void givenOneTonneAndThousandKilograms_WhenCompared_ShouldReturnEqual() {
        Quantity<WeightUnit> tonne = new Quantity<>(1.0, WeightUnit.TONNE);
        Quantity<WeightUnit> kilograms = new Quantity<>(1000.0, WeightUnit.KILOGRAM);
        Assertions.assertTrue(tonne.equals(kilograms));
    }

    @Test
    public void givenOneTonneAndThousandGrams_WhenAdded_ShouldReturnOneThousandAndOneKilograms() {
        Quantity<WeightUnit> tonne = new Quantity<>(1.0, WeightUnit.TONNE);
        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> sum = tonne.add(grams, WeightUnit.KILOGRAM);
        Assertions.assertEquals(new Quantity<>(1001.0, WeightUnit.KILOGRAM), sum);
        Assertions.assertEquals(1001.0, sum.getValue(), 0.0001);
        Assertions.assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    public void givenOneInchAndOneGram_WhenCompared_ShouldReturnFalse() {
        Quantity<LengthUnit> inch = new Quantity<>(1.0, LengthUnit.INCHES);
        Quantity<WeightUnit> gram = new Quantity<>(1.0, WeightUnit.GRAM);
        Assertions.assertFalse(inch.equals((Quantity) gram));
    }

    @Test
    public void givenOneInchAndOneGram_WhenAdded_ShouldThrowException() {
        Quantity<LengthUnit> inch = new Quantity<>(1.0, LengthUnit.INCHES);
        Quantity<WeightUnit> gram = new Quantity<>(1.0, WeightUnit.GRAM);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            inch.add((Quantity) gram);
        });
    }

    // UC11: Volume Measurement Equality, Conversion, and Addition Tests
    @Test
    public void givenOneGallonAndThreePointSevenEightLitres_WhenCompared_ShouldReturnEqual() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litres = new Quantity<>(3.78, VolumeUnit.LITRE);
        Assertions.assertEquals(gallon, litres);
    }

    @Test
    public void givenOneLitreAndThousandMilliliters_WhenCompared_ShouldReturnEqual() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milliliters = new Quantity<>(1000.0, VolumeUnit.MILLILITER);
        Assertions.assertEquals(litre, milliliters);
    }

    @Test
    public void givenOneGallonAndThreePointSevenEightLitres_WhenAdded_ShouldReturnSevenPointFiveSixLitres() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litres = new Quantity<>(3.78, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sum = gallon.add(litres, VolumeUnit.LITRE);
        Assertions.assertEquals(7.56, sum.getValue(), 0.0001);
    }

    @Test
    public void givenOneLitreAndThousandMilliliters_WhenAdded_ShouldReturnTwoLitres() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milliliters = new Quantity<>(1000.0, VolumeUnit.MILLILITER);
        Quantity<VolumeUnit> sum = litre.add(milliliters, VolumeUnit.LITRE);
        Assertions.assertEquals(2.0, sum.getValue(), 0.0001);
        Assertions.assertEquals(VolumeUnit.LITRE, sum.getUnit());
    }

    // UC10: Temperature Measurement Tests
    @Test
    public void givenTwoHundredAndTwelveFahrenheitAndHundredCelsius_WhenCompared_ShouldReturnEqual() {
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> celsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Assertions.assertEquals(fahrenheit, celsius);
    }
    
    @Test
    public void givenThirtyTwoFahrenheitAndZeroCelsius_WhenCompared_ShouldReturnEqual() {
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> celsius = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Assertions.assertEquals(fahrenheit, celsius);
    }
}
