package com.bridgelabz.qmaservice.enums;

import java.util.function.BinaryOperator;

public enum ArithmeticOperation {
    ADD((a, b) -> a + b),
    SUBTRACT((a, b) -> a - b),
    MULTIPLY((a, b) -> a * b),
    DIVIDE((a, b) -> {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    });

    private final BinaryOperator<Double> operator;

    ArithmeticOperation(BinaryOperator<Double> operator) {
        this.operator = operator;
    }

    public double apply(double a, double b) {
        return operator.apply(a, b);
    }
}
