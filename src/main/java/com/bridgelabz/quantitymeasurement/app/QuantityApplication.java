package com.bridgelabz.quantitymeasurement.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bridgelabz.quantitymeasurement.model.Quantity;
import com.bridgelabz.quantitymeasurement.enums.LengthUnit;

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
    }
}
