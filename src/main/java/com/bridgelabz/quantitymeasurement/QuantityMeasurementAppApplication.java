package com.bridgelabz.quantitymeasurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bridgelabz.quantitymeasurement.model.Feet;
import com.bridgelabz.quantitymeasurement.model.Inches;

@SpringBootApplication
public class QuantityMeasurementAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuantityMeasurementAppApplication.class, args);

        Feet feet = new Feet(1.0);
        Inches inches = new Inches(12.0);

        System.out.println("1 Feet == 12 Inches : " + feet.equals(inches));

        Inches inch = new Inches(12.0);
        Feet foot = new Feet(1.0);

        System.out.println("12 Inches == 1 Feet : " + inch.equals(foot));

        Feet feet1 = new Feet(2.0);
        Feet feet2 = new Feet(2.0);

        System.out.println("2 Feet == 2 Feet : " + feet1.equals(feet2));

        Inches inches1 = new Inches(10.0);
        Inches inches2 = new Inches(10.0);

        System.out.println("10 Inches == 10 Inches : " + inches1.equals(inches2));

        Feet differentFeet = new Feet(1.0);
        Inches differentInches = new Inches(10.0);

        System.out.println("1 Feet == 10 Inches : " + differentFeet.equals(differentInches));
    }
}