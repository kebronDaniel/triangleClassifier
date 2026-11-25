package com.triangle.classifier.domain;

import java.math.BigDecimal;

public class InvalidTriangleException extends RuntimeException {
    private InvalidTriangleException(String message) {
        super(message);
    }

    public static InvalidTriangleException nonPositiveSide(String side, BigDecimal value){
        return new InvalidTriangleException("Side " + side + " has value of " + value + " . Value should be positive");
    }

    public static InvalidTriangleException sideOutOfRange(String side, BigDecimal value){
        return new InvalidTriangleException("Side" + side + " exceeds the limit with value of " + value);
    }

    public static InvalidTriangleException missingSide(String side, BigDecimal value){
        return new InvalidTriangleException("Side " + side + " should not be " + value);
    }

    public static InvalidTriangleException notATriangle(BigDecimal value){
        return new InvalidTriangleException("Invalid Triangle, value " + value + " is greater than the sum of the two");
    }

}
