package com.bridgelabz.quantitymeasurement.enums;

import java.util.function.DoubleBinaryOperator;

// UC13: Enum-Based Operation Dispatch for Arithmetic Operations
public enum ArithmeticOperation {
    ADD((val1, val2) -> val1 + val2),
    SUBTRACT((val1, val2) -> val1 - val2),
    DIVIDE((val1, val2) -> {
        if (Math.abs(val2) < 0.0001) { // DIFFERENCE threshold
            throw new ArithmeticException("Cannot divide by zero quantity.");
        }
        return val1 / val2;
    });

    private final DoubleBinaryOperator operator;

    ArithmeticOperation(DoubleBinaryOperator operator) {
        this.operator = operator;
    }

    public double apply(double val1, double val2) {
        return operator.applyAsDouble(val1, val2);
    }
}
