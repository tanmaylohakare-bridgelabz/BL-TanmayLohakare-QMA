package com.bridgelabz.quantitymeasurement.enums;

import com.bridgelabz.quantitymeasurement.interfaces.Unit;

// UC10: Temperature Measurement Support Enum demonstrating Polymorphism
public enum TemperatureUnit implements Unit {
    FAHRENHEIT {
        @Override
        public double convertToBaseUnit(double value) {
            return (value - 32) * 5 / 9;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return (baseValue * 9 / 5) + 32;
        }
    },
    CELSIUS {
        @Override
        public double convertToBaseUnit(double value) {
            return value;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue;
        }
    };
}
