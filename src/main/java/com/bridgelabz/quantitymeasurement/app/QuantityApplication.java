package com.bridgelabz.quantitymeasurement.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.enums.LengthUnit;
import com.bridgelabz.quantitymeasurement.enums.WeightUnit;

@SpringBootApplication(scanBasePackages = "com.bridgelabz.quantitymeasurement")
public class QuantityApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuantityApplication.class, args);

        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("1 Feet == 12 Inches : " + feet.equals(inches));

        Quantity<LengthUnit> inch = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> foot = new Quantity<>(1.0, LengthUnit.FEET);

        System.out.println("12 Inches == 1 Feet : " + inch.equals(foot));

        Quantity<LengthUnit> feet1 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);

        System.out.println("2 Feet == 2 Feet : " + feet1.equals(feet2));

        Quantity<LengthUnit> inches1 = new Quantity<>(10.0, LengthUnit.INCHES);
        Quantity<LengthUnit> inches2 = new Quantity<>(10.0, LengthUnit.INCHES);

        System.out.println("10 Inches == 10 Inches : " + inches1.equals(inches2));

        Quantity<LengthUnit> differentFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> differentInches = new Quantity<>(10.0, LengthUnit.INCHES);

        System.out.println("1 Feet == 10 Inches : " + differentFeet.equals(differentInches));

        Quantity<LengthUnit> threeFeet = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> oneYard = new Quantity<>(1.0, LengthUnit.YARD);

        System.out.println("3 Feet == 1 Yard : " + threeFeet.equals(oneYard));

        Quantity<LengthUnit> twoInches = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> fiveCentimeters = new Quantity<>(5.0, LengthUnit.CENTIMETER);

        System.out.println("2 Inches == 5 Centimeters : " + twoInches.equals(fiveCentimeters));

        System.out.println("\n--- UC5 Conversions ---");
        Quantity<LengthUnit> convertedYard = oneYard.convertTo(LengthUnit.INCHES);
        System.out.println("Converted 1 Yard to Inches: " + convertedYard.getValue() + " " + convertedYard.getUnit());
        
        System.out.println("\n--- UC6 Addition ---");
        Quantity<LengthUnit> sumInches = twoInches.add(twoInches);
        System.out.println("2 Inches + 2 Inches = " + sumInches.getValue() + " " + sumInches.getUnit());

        Quantity<LengthUnit> sumFeet = foot.add(twoInches);
        System.out.println("1 Foot + 2 Inches = " + sumFeet.getValue() + " " + sumFeet.getUnit());

        Quantity<LengthUnit> sumMixed = twoInches.add(fiveCentimeters);
        System.out.println("2 Inches + 5 Centimeters = " + sumMixed.getValue() + " " + sumMixed.getUnit());

        System.out.println("\n--- UC7 Addition with Target Unit ---");
        Quantity<LengthUnit> sumTargetCm = twoInches.add(twoInches, LengthUnit.CENTIMETER);
        System.out.println("2 Inches + 2 Inches (in CM) = " + sumTargetCm.getValue() + " " + sumTargetCm.getUnit());

        Quantity<LengthUnit> sumTargetInches = foot.add(twoInches, LengthUnit.INCHES);
        System.out.println("1 Foot + 2 Inches (in Inches) = " + sumTargetInches.getValue() + " " + sumTargetInches.getUnit());

        System.out.println("\n--- UC9 Weight Addition ---");
        Quantity<WeightUnit> tonne = new Quantity<>(1.0, WeightUnit.TONNE);
        Quantity<WeightUnit> grams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> sumWeight = tonne.add(grams, WeightUnit.KILOGRAM);
        System.out.println("1 Tonne + 1000 Grams (in Kilogram) = " + sumWeight.getValue() + " " + sumWeight.getUnit());
    }
}
